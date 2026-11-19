package game2025;

import java.awt.Graphics;

/**
 * 坦克装饰器（抽象层）。
 *
 * 原来的版本构造函数带 (ax, ay)，但坐标其实全部转发给被装饰对象，
 * 这里按上课代码简化成只传被装饰对象，同时在转发链里补上了
 *   createBullet()、getSpeed()/setSpeed()、receiveHit()、getInner()/setInner()
 * 这几个“buff 装饰器”需要用到的接口。
 */
public abstract class Decorator extends Spirite {

	/** 被装饰的精灵 */
	protected Spirite spirite;

	public Decorator(Spirite s) {
		super(s.getX(), s.getY());
		spirite = s;
	}

	public Spirite getInner() { return spirite; }
	public void setInner(Spirite s) { spirite = s; }

	@Override
	public void setDir(DIR adir) { spirite.setDir(adir);}
	@Override
	public DIR getDir() { return spirite.getDir();}
	@Override
	public int getX() {return spirite.getX();}
	@Override
	public int getY() {return spirite.getY();}
	@Override
	public void setX(int ax) {spirite.setX(ax);}
	@Override
	public void setY(int ay) {spirite.setY(ay);}
	@Override
	public int getSpeed() { return spirite.getSpeed(); }
	@Override
	public void setSpeed(int s) { spirite.setSpeed(s); }
	@Override
	public void move() {spirite.move();}
	@Override
	public void draw(Graphics g) {spirite.draw(g);}
	@Override
	public Bullet createBullet() { return spirite.createBullet(); }
	@Override
	public Bullet fire() {return spirite.fire();}
	@Override
	public boolean receiveHit() { return spirite.receiveHit(); }

	/**
	 * 这一层装饰在 HUD 上怎么显示。
	 * 返回 null 表示它不参与计时（比如纯视觉的护盾条），不会被 GameFrame 自动摘掉。
	 */
	public String getBuffName() { return null; }

	/** HUD 里显示的贴图格子（见 Icons 类） */
	public int getIconCol() { return 0; }
	public int getIconRow() { return 0; }

	/**
	 * 从装饰链上摘掉某一层，返回新的链头。
	 * 装饰链是“外层包内层”，所以要递归到最里面、再一层层接回来。
	 */
	public static Spirite remove(Spirite root, Decorator target) {
		if (root == target) {
			return target.getInner();
		}
		if (root instanceof Decorator) {
			Decorator d = (Decorator) root;
			Spirite inner = d.getInner();
			Spirite newInner = remove(inner, target);
			if (newInner != inner) {
				d.setInner(newInner);
			}
		}
		return root;
	}

	/** 把所有装饰器都剥掉，还原成最初的精灵 */
	public static Spirite unwrapAll(Spirite root) {
		Spirite s = root;
		while (s instanceof Decorator) {
			s = ((Decorator) s).getInner();
		}
		return s;
	}
}
