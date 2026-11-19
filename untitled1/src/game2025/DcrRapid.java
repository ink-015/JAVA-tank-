package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 连发 buff：开火冷却从 10 帧降到 3 帧。
 *
 * 装饰器自己接管 fire()，每次要子弹时直接向被装饰对象 createBullet()，
 * 从而绕开内层的冷却限制——不改内层的任何字段。
 */
public class DcrRapid extends Decorator {

	/** 几帧一发 */
	private static final int RAPID_COOL = 3;
	private int rapidCool = 0;

	public DcrRapid(Spirite s) {
		super(s);
	}

	@Override
	public void move() {
		super.move();
		rapidCool++;
	}

	@Override
	public Bullet fire() {
		if (rapidCool > RAPID_COOL) {
			rapidCool = 0;
			return spirite.createBullet();
		}
		return null;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 左上角小黄点，表示正在连发
		g.setColor(new Color(255, 220, 80, 220));
		g.fillOval(getX() + 1, getY() + 1, 6, 6);
		g.setColor(new Color(120, 90, 10, 200));
		g.drawOval(getX() + 1, getY() + 1, 6, 6);
	}

	@Override
	public String getBuffName() { return "连发"; }

	@Override
	public int getIconCol() { return Icons.RAPID_COL; }

	@Override
	public int getIconRow() { return Icons.RAPID_ROW; }
}
