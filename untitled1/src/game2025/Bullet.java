package game2025;

import java.awt.Graphics;

public abstract class Bullet extends VisibleItem {
	protected DIR dir;
	protected int v;
	/** 这一发打掉对方多少血 */
	protected int damage = 1;
	/** 命中判定半径 */
	protected int hitRadius = 30;

	public Bullet(int ax, int ay) {
		super(ax,ay);
		v = 6;
	}
	public void setDir(DIR adir) { dir = adir;}
	public DIR getDir() { return dir; }
	public int getDamage() { return damage; }
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int ax) {x = ax;}
	public void setY(int ay) {y = ay;}

	public void draw(Graphics g) {
		g.drawImage(Resourse.getImg(), x, y, x+34, y+34,
				5*34    , 6*34,
				6*34 , 7*34, null);
	}

	/**
	 * 是否打到坦克。
	 * 注意：原版这里直接读字段 tank.x，一旦坦克被装饰器包起来，
	 * 装饰器自己的 x 是没被更新的，判定就会错位，所以改成走 getX()。
	 */
	public boolean isHitTank(Spirite tank) {
		double dx = Math.pow(tank.getX()-x,2);
		double dy = Math.pow(tank.getY()-y,2);
		double d = Math.sqrt(dx+dy);

		return d < hitRadius;
	}

	public void move() {
		switch(dir) {
		case UP:  y -= v ;	break;
		case RIGHT: x += v;break;
		case DOWN: y += v; break;
		case LEFT: x -= v;break;
		}
	}

	public boolean isOutOfBounds() {
		if( (y<=34) || (x >= GameFrame.WIDTH-34) ||
				(y>=GameFrame.HEIGHT-34) ||(x <= 0))
			return true;
		else return false;
	}
}
