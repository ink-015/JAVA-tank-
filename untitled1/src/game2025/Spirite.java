package game2025;

import java.awt.Graphics;

public abstract class Spirite extends VisibleItem {

	protected final int WIDTH = 20;
	protected final int HEIGHT = 20;
	protected DIR dir; // 0 up 1 right 2 down 3 left

	protected int flag = 0;
	protected int v = 0;
	/** 开火冷却，每帧 +1，超过 ARMS_COOL 才能再打一发 */
	private int coolTime = 0;
	private static final int ARMS_COOL = 10;

	public Spirite(int ax, int ay) {
		super(ax, ay);
		v = 2;
		dir = DIR.UP;
	}

	public void setDir(DIR adir) { dir = adir;}
	public DIR getDir() { return dir;}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int ax) {x = ax;}
	public void setY(int ay) {y = ay;}

	public int getSpeed() { return v; }
	public void setSpeed(int s) { v = s; }

	public void move() {
		switch(dir) {
		case UP:
			if(y>34) y -= v ;
			else dir = DIR.DOWN;
			break;
		case RIGHT:
			if(x < GameFrame.WIDTH-34) x += v;
			else dir=DIR.LEFT;
			break;
		case DOWN:
			if(y<GameFrame.HEIGHT-34) y += v;
			else dir=DIR.UP;
			break;
		case LEFT:
			if(x > 0) x -= v;
			else dir = DIR.RIGHT;
			break;
		}
		coolTime++;
	}

	public abstract void draw(Graphics g);

	/**
	 * 制造一颗子弹。装饰器（比如强化弹）可以覆盖它，换成别的子弹。
	 * 单独抽出来是为了让连发这种装饰器能绕开冷却直接要子弹。
	 */
	public Bullet createBullet() {
		Bullet b = new Bullet_A(x, y);
		b.setDir(dir);
		return b;
	}

	public Bullet fire() {
		Bullet b = null;
		if (coolTime > ARMS_COOL) {
			b = createBullet();
			coolTime = 0;
		}
		return b;
	}

	/**
	 * 被子弹击中。返回 true 表示这次伤害生效；
	 * 护盾装饰器会返回 false，把这一下挡掉。
	 */
	public boolean receiveHit() {
		return true;
	}
}
