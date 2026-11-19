package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 减速 buff（负面）：踩到陷阱箱会中招，移动速度减半。
 * 和加速对称实现，说明装饰器既能“加好处”也能“加坏处”。
 */
public class DcrSlow extends Decorator {

	private static final int FACTOR = 2;

	public DcrSlow(Spirite s) {
		super(s);
	}

	@Override
	public void move() {
		int old = spirite.getSpeed();
		spirite.setSpeed(Math.max(1, old / FACTOR));
		try {
			spirite.move();
		} finally {
			spirite.setSpeed(old);
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(new Color(160, 170, 200, 90));
		g.fillOval(getX() + 2, getY() + 2, 30, 30);
		g.setColor(new Color(200, 220, 255, 200));
		g.drawOval(getX() + 2, getY() + 2, 30, 30);
	}

	@Override
	public String getBuffName() { return "减速"; }

	@Override
	public int getIconCol() { return Icons.TRAP_COL; }

	@Override
	public int getIconRow() { return Icons.TRAP_ROW; }
}
