package game2025;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

/**
 * 游戏主窗口。
 *
 * 相比原版，改动集中在四块：
 *  1. 玩家坦克的类型从 Tank 改成了 Spirite —— 它可以被装饰器一层层包起来；
 *  2. 子弹现在会被砖块挡住、能打碎砖块，砖块碎掉可能掉道具；
 *  3. 地上有掉落物，捡到后以“装饰器”的形式给坦克加 buff，到期自动摘掉；
 *  4. 加了生命值和分数，护盾 / 回血才有意义。
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements KeyListener {

	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;

	/** 场上同时存在的敌军上限（原版用 NPCTank.getCount() 判断，会因为 count 只增不减而停止刷怪） */
	private static final int MAX_NPC = 6;
	/** 一个 buff 持续多少帧（每帧 100ms） */
	private static final int BUFF_FRAMES = 260;

	private Image offScreenImage = null;
	private Graphics gOffScreen = null;

	private int[][] pos = {{100,50},{300,50},{500,50}};
	private ArrayList<NPCTank> npcTanks = new ArrayList<NPCTank>();

	/** 玩家坦克：可能被 DcrShield / DcrSpeed / ... 一层层装饰 */
	private Spirite player = new Tank(WIDTH/2, HEIGHT-50);

	private ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
	private ArrayList<Bullet> npcBullets = new ArrayList<Bullet>();
	private ArrayList<Cartoon> cartoons = new ArrayList<Cartoon>();

	/** 地上还没被捡走的掉落物 */
	private ArrayList<Gameprop> props = new ArrayList<Gameprop>();
	/** 玩家身上正在生效的 buff */
	private ArrayList<BuffSlot> buffs = new ArrayList<BuffSlot>();

	private Map map;
	private Random rnd = new Random();

	private int life = 3;
	private int score = 0;

	public GameFrame() {

		super("Tank Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setFocusable(true);

		map = new Map();
		try {
			map.loadData("map1.txt");
		} catch (IOException e) {
			// 原来这里只打印堆栈，看不出发生了什么，这里给一句人话
			System.err.println("地图加载失败：" + e.getMessage());
			e.printStackTrace();
		}

		setVisible(true);

		FrameThread customThread = new FrameThread();
		customThread.start();
	}

	private class FrameThread extends Thread {

		public void run() {
			while (true) {
				try {
					updateGame();
					repaint();
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ==================== 主循环 ====================

	/**
	 * 主循环。
	 * 加上 synchronized 是为了和 EDT 上的 paint() 互斥：
	 * 否则一边在给列表增删元素、一边在遍历绘制，偶尔会抛
	 * ConcurrentModificationException（原版就有这个隐患）。
	 */
	private synchronized void updateGame() {
		spawnAndFireNpc();
		updateNpcBullets();

		for (NPCTank tank : npcTanks) {
			tank.move();
			if (Resourse.getInstance().getDis(tank, player) < 100) {
				tank.setStrategy(new FollowMoving());
			}
		}

		player.move();
		updatePlayerBullets();
		updateProps();
		updateBuffs();
	}

	private void spawnAndFireNpc() {
		// 刷怪：用场上实际数量控制，而不是永远增长的计数器
		if (npcTanks.size() < MAX_NPC && rnd.nextInt(100) < 9) {
			int p = rnd.nextInt(pos.length);
			NPCTank t = new NPCTank(pos[p][0], pos[p][1]);
			t.setVisibleItem(player);
			t.setDir(DIR.DOWN);
			t.setStrategy(new RandomMoving());
			npcTanks.add(t);
		}

		if (!npcTanks.isEmpty() && rnd.nextInt(100) < 12) {
			int index = rnd.nextInt(npcTanks.size());
			Bullet b = npcTanks.get(index).fire();
			if (b != null) {
				npcBullets.add(b);
			}
		}
	}

	/** 敌军子弹：撞墙消失、打碎砖块、击中玩家 */
	private void updateNpcBullets() {
		for (int i = npcBullets.size() - 1; i >= 0; --i) {
			Bullet b = npcBullets.get(i);
			b.move();

			if (b.isOutOfBounds()) {
				npcBullets.remove(i);
				continue;
			}
			if (hitBlock(b)) {
				npcBullets.remove(i);
				continue;
			}
			if (b.isHitTank(player)) {
				npcBullets.remove(i);
				hitPlayer(b.getDamage());
			}
		}
	}

	/** 玩家子弹：打砖块、打敌人 */
	private void updatePlayerBullets() {
		for (int i = playerBullets.size() - 1; i >= 0; --i) {
			Bullet b = playerBullets.get(i);
			b.move();

			if (b.isOutOfBounds()) {
				playerBullets.remove(i);
				continue;
			}
			if (hitBlock(b)) {
				playerBullets.remove(i);
				continue;
			}

			boolean consumed = false;
			for (int j = npcTanks.size() - 1; j >= 0; --j) {
				NPCTank t = npcTanks.get(j);
				if (b.isHitTank(t)) {
					playerBullets.remove(i);
					if (t.hurt(b.getDamage())) {
						killNpc(j);
					} else {
						cartoons.add(new Cartoon_E(t.getX(), t.getY()));
					}
					consumed = true;
					break;
				}
			}
			if (consumed) {
				continue;
			}
		}
	}

	// ==================== 砖块 ====================

	/**
	 * 子弹打在砖块上。返回 true 表示这颗子弹被吃掉了。
	 * 水、草不挡子弹，所以会被跳过。
	 */
	private boolean hitBlock(Bullet b) {
		if (map == null) {
			return false;
		}
		Block block = map.hitPoint(b.getCenterX(), b.getCenterY());
		if (block == null || !block.isBlocksBullet()) {
			return false;
		}
		if (block.hit()) {
			breakBlock(block);
		}
		return true;
	}

	private void breakBlock(Block block) {
		map.remove(block);
		cartoons.add(new Cartoon_E(block.getX(), block.getY()));
		score += 5;

		// 砖块自己决定掉什么（惊喜砖 / 炸药砖在这一步掉道具）
		for (Gameprop p : block.dropItems()) {
			props.add(p);
		}

		int radius = block.getExplodeRadius();
		if (radius > 0) {
			explode(block, radius);
		}
	}

	/** 炸药砖爆炸：范围内的敌人被炸掉，范围内的砖块被震碎 */
	private void explode(Block center, int radius) {
		for (int i = npcTanks.size() - 1; i >= 0; --i) {
			NPCTank t = npcTanks.get(i);
			if (Resourse.getInstance().getDis(t, center) < radius) {
				npcTanks.remove(i);
				NPCTank.decreaseCount();
				cartoons.add(new Cartoon_E(t.getX(), t.getY()));
				score += 10;
			}
		}

		// 先收集再删除：不能在遍历 map.getBlocks() 的时候直接 remove
		ArrayList<Block> toBreak = new ArrayList<Block>();
		for (Block b : map.getBlocks()) {
			if (b != center && b.isDestroyable()
					&& Resourse.getInstance().getDis(b, center) < radius * 0.8) {
				toBreak.add(b);
			}
		}
		for (Block b : toBreak) {
			map.remove(b);
			cartoons.add(new Cartoon_E(b.getX(), b.getY()));
			score += 5;
		}
	}

	// ==================== 掉落物与 buff ====================

	private void updateProps() {
		for (int i = props.size() - 1; i >= 0; --i) {
			Gameprop prop = props.get(i);
			prop.update();
			if (prop.isExpired()) {
				props.remove(i);
				continue;
			}
			if (prop.isHitTank(player)) {
				props.remove(i);
				pickUp(prop);
			}
		}
	}

	/** 捡起道具：把对应的装饰器套到玩家坦克上 */
	private void pickUp(Gameprop prop) {
		if (prop.getLifeBonus() > 0) {
			life += prop.getLifeBonus();
		}

		Decorator deco = prop.createBuff(player);
		if (deco == null) {
			return;                        // 比如回血道具，不是持续 buff
		}

		// 同类 buff 不叠加：先把旧的摘掉，否则越套越深
		removeBuffOfType(deco.getClass());

		player = deco;
		buffs.add(new BuffSlot(deco, BUFF_FRAMES));
	}

	private void updateBuffs() {
		for (int i = buffs.size() - 1; i >= 0; --i) {
			BuffSlot slot = buffs.get(i);
			slot.tick();
			if (slot.isExpired()) {
				player = removeDecorator(player, slot.getDecorator());
				buffs.remove(i);
			}
		}
	}

	/**
	 * 从装饰链里摘掉某一层（具体实现放在 Decorator 里，方便单独测试）。
	 */
	private Spirite removeDecorator(Spirite root, Decorator target) {
		return Decorator.remove(root, target);
	}

	private void removeBuffOfType(Class<?> type) {
		for (int i = buffs.size() - 1; i >= 0; --i) {
			Decorator old = buffs.get(i).getDecorator();
			if (old.getClass() == type) {
				player = removeDecorator(player, old);
				buffs.remove(i);
			}
		}
	}

	/** 护盾挡下伤害后，把用掉的那层护盾摘掉 */
	private void removeUsedShield() {
		for (int i = 0; i < buffs.size(); i++) {
			Decorator d = buffs.get(i).getDecorator();
			if (d instanceof DcrShield && ((DcrShield) d).isUsed()) {
				player = removeDecorator(player, d);
				buffs.remove(i);
				return;
			}
		}
	}

	// ==================== 玩家生死 ====================

	private void hitPlayer(int damage) {
		if (!player.receiveHit()) {
			// 护盾把这一下挡掉了
			removeUsedShield();
			return;
		}
		life -= damage;
		cartoons.add(new Cartoon_E(player.getX(), player.getY()));
		if (life <= 0) {
			life = 3;
			score = 0;
		}
		respawnPlayer();
	}

	/** 复活：先把所有装饰器拆干净，还原本来的坦克 */
	private void respawnPlayer() {
		player = Decorator.unwrapAll(player);
		player.setX(WIDTH/2);
		player.setY(HEIGHT-50);
		player.setDir(DIR.UP);
		buffs.clear();
	}

	private void killNpc(int index) {
		NPCTank t = npcTanks.remove(index);
		NPCTank.decreaseCount();
		cartoons.add(new Cartoon_E(t.getX(), t.getY()));
		score += 10;

		// 击败敌人掉落：多数是道具，少数是陷阱箱
		int r = rnd.nextInt(100);
		if (r < 28) {
			props.add(Gameprop.randomProp(t.getX(), t.getY()));
		} else if (r < 40) {
			props.add(new GamePropDown(t.getX(), t.getY()));
		}
	}

	// ==================== 绘制 ====================

	public synchronized void paint(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(WIDTH, HEIGHT);
			gOffScreen = offScreenImage.getGraphics();
		}
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);

		if (map != null) {
			map.draw(gOffScreen);
		}

		for (Gameprop prop : props) {
			prop.draw(gOffScreen);
		}

		player.draw(gOffScreen);

		for (NPCTank npcTank : npcTanks) {
			npcTank.draw(gOffScreen);
		}
		for (Bullet b : playerBullets) {
			b.draw(gOffScreen);
		}
		for (Bullet b : npcBullets) {
			b.draw(gOffScreen);
		}

		for (int i = cartoons.size() - 1; i >= 0; --i) {
			Cartoon cartoon = cartoons.get(i);
			cartoon.draw(gOffScreen);
			if (cartoon.isFinish()) {
				cartoons.remove(i);
			}
		}

		drawHud(gOffScreen);

		g.drawImage(offScreenImage, 0, 0, null);
	}

	/** 左上角生命、右上角分数、左边 buff 列表 */
	private void drawHud(Graphics g) {
		for (int i = 0; i < life && i < 10; i++) {
			Resourse.drawTile(g, Icons.HEAL_COL, Icons.HEAL_ROW, 10 + i * 26, 8, 22);
		}

		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.BOLD, 16));
		g.drawString("SCORE " + score, WIDTH - 150, 26);

		int y = 44;
		for (BuffSlot slot : buffs) {
			Decorator d = slot.getDecorator();
			if (d.getBuffName() == null) {
				continue;
			}
			Resourse.drawTile(g, d.getIconCol(), d.getIconRow(), 10, y, 20);

			g.setColor(new Color(255, 255, 255, 50));
			g.fillRect(34, y + 6, 80, 8);
			g.setColor(d instanceof DcrSlow ? new Color(150, 170, 220) : new Color(120, 220, 140));
			g.fillRect(34, y + 6, (int) (80 * slot.getRatio()), 8);

			g.setColor(Color.WHITE);
			g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
			g.drawString(d.getBuffName(), 120, y + 15);
			y += 24;
		}
	}

	// ==================== 键盘 ====================

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_UP:    player.setDir(DIR.UP);break;
			case KeyEvent.VK_RIGHT: player.setDir(DIR.RIGHT);break;
			case KeyEvent.VK_DOWN:  player.setDir(DIR.DOWN);break;
			case KeyEvent.VK_LEFT:  player.setDir(DIR.LEFT);break;
			case KeyEvent.VK_SPACE:
				Bullet b = player.fire();
				if (b != null) {
					playerBullets.add(b);
				}
				break;
			case KeyEvent.VK_R:     // 重开
				life = 3;
				score = 0;
				respawnPlayer();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
