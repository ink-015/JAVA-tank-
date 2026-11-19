package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 加速 buff：移动速度翻倍。
 *
 * 速度 v 存在被装饰的 Spirite 里，装饰器不去改它的字段，
 * 而是在每次 move() 时临时提速、移完再还原——这样摘掉装饰器时不会有残留。
 */
public class DcrSpeed extends Decorator {

	/** 速度倍数 */
	private static final int FACTOR = 2;
	private int phase = 0;

	public DcrSpeed(Spirite s) {
		super(s);
	}

	@Override
	public void move() {
		int old = spirite.getSpeed();
		spirite.setSpeed(old * FACTOR);
		try {
			spirite.move();
		} finally {
			spirite.setSpeed(old);       // 还原，保证移除 buff 后速度正常
		}
		phase++;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 身后的速度线
		g.setColor(new Color(255, 235, 120, 150));
		int cx = getX() + 17;
		int cy = getY() + 17;
		switch (getDir()) {
			case UP:
				g.drawLine(cx - 10, cy + 18, cx - 10, cy + 30);
				g.drawLine(cx + 10, cy + 18, cx + 10, cy + 30);
				break;
			case DOWN:
				g.drawLine(cx - 10, cy - 30, cx - 10, cy - 18);
				g.drawLine(cx + 10, cy - 30, cx + 10, cy - 18);
				break;
			case LEFT:
				g.drawLine(cx + 18, cy - 10, cx + 30, cy - 10);
				g.drawLine(cx + 18, cy + 10, cx + 30, cy + 10);
				break;
			case RIGHT:
				g.drawLine(cx - 30, cy - 10, cx - 18, cy - 10);
				g.drawLine(cx - 30, cy + 10, cx - 18, cy + 10);
				break;
		}
	}

	@Override
	public String getBuffName() { return "加速"; }

	@Override
	public int getIconCol() { return Icons.SPEED_COL; }

	@Override
	public int getIconRow() { return Icons.SPEED_ROW; }
}
