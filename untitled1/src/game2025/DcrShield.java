package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 护盾 buff：能挡下一次伤害。
 *
 * 装饰器拦截 receiveHit()：第一次被打时返回 false（伤害不生效），
 * 之后护盾就“用掉了”，GameFrame 会把它从装饰链上摘掉。
 */
public class DcrShield extends Decorator {

	private boolean used = false;
	/** 挡下伤害时的短暂闪光 */
	private int flash = 0;

	public DcrShield(Spirite s) {
		super(s);
	}

	public boolean isUsed() { return used; }

	@Override
	public boolean receiveHit() {
		if (!used) {
			used = true;
			flash = 12;
			return false;          // 挡住了
		}
		return spirite.receiveHit();
	}

	@Override
	public void move() {
		super.move();
		if (flash > 0) {
			flash--;
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		int cx = getX() + 17;
		int cy = getY() + 17;
		if (used) {
			g.setColor(new Color(120, 120, 120, 70));      // 用掉的护盾残影
			g.drawOval(cx - 22, cy - 22, 44, 44);
			return;
		}
		if (flash > 0) {
			g.setColor(new Color(210, 240, 255, 230));
			g.fillOval(cx - 24, cy - 24, 48, 48);
		}
		g.setColor(new Color(90, 190, 255, 210));
		g.drawOval(cx - 21, cy - 21, 42, 42);
		g.setColor(new Color(150, 225, 255, 140));
		g.drawOval(cx - 18, cy - 18, 36, 36);
	}

	@Override
	public String getBuffName() { return "护盾"; }

	@Override
	public int getIconCol() { return Icons.SHIELD_COL; }

	@Override
	public int getIconRow() { return Icons.SHIELD_ROW; }
}
