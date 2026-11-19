package game2025;

import java.awt.Graphics;

/**
 * 所有可见对象的基类：只要能在屏幕上画出来，就继承它。
 */
public abstract class VisibleItem {
	/** 地图一格的大小（贴图集中每个图块 34x34） */
	public static final int SIZE = 34;

	protected int x;
	protected int y;

	public VisibleItem(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {return x;}
	public int getY() {return y;}
	public int getCenterX() {return x + SIZE / 2;}
	public int getCenterY() {return y + SIZE / 2;}

	public abstract void draw(Graphics g);

	/** 两个对象中心点之间的距离，碰撞判断用 */
	public double distanceTo(VisibleItem other) {
		double dx = getCenterX() - other.getCenterX();
		double dy = getCenterY() - other.getCenterY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	/** 点 (px,py) 是否落在自己这一格里 */
	public boolean contains(int px, int py) {
		return px >= x && px < x + SIZE && py >= y && py < y + SIZE;
	}
}
