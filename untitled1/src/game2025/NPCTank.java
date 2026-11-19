package game2025;

import java.awt.Graphics;
import java.util.Random;

public class NPCTank extends Spirite {
	private static int count = 0;
	protected Random rnd = null;
	protected VisibleItem tank;
	private IStrategy strategy;

	/** 敌军血量：普通子弹 1 点，两发打掉；强化弹 2 点，一发打掉 */
	private int hp = 2;

	public NPCTank(int ax, int ay) {
		super(ax, ay);
		v = 2;
		rnd = new Random();
		count++;
	}

	public void setVisibleItem(VisibleItem item) {tank = item;}
	public void setStrategy(IStrategy s){strategy = s;}

	public static int getCount() {return count;}

	/**
	 * 原版只有 count++，被打死后也不减，于是“场上最多 50 个”很快就变成
	 * “再也不会刷出敌人”。这里补上减少的接口，GameFrame 移除敌军时调用它。
	 */
	public static void decreaseCount() {
		if (count > 0) {
			count--;
		}
	}

	public int getHp() { return hp; }

	/** 挨了一次伤害，返回 true 表示被打死了 */
	public boolean hurt(int damage) {
		hp -= damage;
		return hp <= 0;
	}

	public void move() {
		super.move();
		if (strategy != null && tank != null) {
			DIR temDir = strategy.calculateDir(x, y, tank.getX(), tank.getY());
			if (temDir != null) {
				setDir(temDir);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(Resourse.getImg(), x, y, x+34, y+34,
				68*dir.ordinal()+flag*34    , 0,
				68*dir.ordinal()+flag*34+34 , 34, null);
		flag++;
		flag = flag==2?0:1;
		// 血条：被打过一发之后才显示，免得画面太花
		if (hp < 2) {
			g.setColor(new java.awt.Color(60, 20, 20, 200));
			g.fillRect(x + 4, y - 5, 26, 4);
			g.setColor(new java.awt.Color(240, 90, 90));
			g.fillRect(x + 4, y - 5, hp * 13, 4);
		}
	}
}
