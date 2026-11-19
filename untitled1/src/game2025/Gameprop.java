package game2025;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * 掉落物（Component）。
 *
 * 地上一个道具 = 一个 Gameprop 子类；坦克碰到它就调用 createBuff()，
 * 拿到一个“装饰器”套到玩家坦克身上。所以“掉落物生成 buff”这条链上是：
 *     掉落物 --createBuff--> 装饰器 --套在 Tank 上--> 能力变化
 *
 * 原版这个类只有空的 draw()，这里补上了图标、存活时间、碰撞和工厂方法。
 */
public abstract class Gameprop extends VisibleItem {

	/** 在地上待多久（帧）后消失 */
	public static final int LIFE = 520;

	private static final Random RND = new Random();

	protected int life = LIFE;
	/** 图标在贴图集里的格子 */
	protected int iconCol;
	protected int iconRow;

	public Gameprop(int x, int y, int iconCol, int iconRow) {
		super(x, y);
		this.iconCol = iconCol;
		this.iconRow = iconRow;
	}

	/** 每帧调用一次 */
	public void update() { life--; }

	public boolean isExpired() { return life <= 0; }

	@Override
	public void draw(Graphics g) {
		if (life < 90 && (life / 6) % 2 == 0) {
			return;                       // 快消失了，闪烁一下
		}
		if (iconCol >= 0) {
			Resourse.drawTile(g, iconCol, iconRow, x, y, 34);
		}
		g.setColor(new Color(255, 250, 200, 120));
		g.drawRect(x, y, 33, 33);         // 淡淡的边框，免得和地形混在一起
	}

	/** 坦克有没有碰到自己 */
	public boolean isHitTank(VisibleItem tank) {
		return Resourse.getInstance().getDis(this, tank) < 28;
	}

	/** 吃掉后加几条命（只有回血道具会 >0） */
	public int getLifeBonus() { return 0; }

	/**
	 * 吃掉后给坦克套上的装饰器。
	 * 返回 null 表示这个道具不是“持续 buff”，由 GameFrame 走别的逻辑。
	 */
	public abstract Decorator createBuff(Spirite tank);

	public abstract String getName();

	/** 随机掉落一个正面道具：击败敌人、打碎特殊砖块时用 */
	public static Gameprop randomProp(int px, int py) {
		switch (RND.nextInt(5)) {
			case 0:  return new PropShield(px, py);
			case 1:  return new PropSpeed(px, py);
			case 2:  return new PropRapid(px, py);
			case 3:  return new PropPower(px, py);
			default: return new PropHeal(px, py);
		}
	}
}
