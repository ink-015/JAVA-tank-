package game2025;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 强化弹 buff：把打出来的子弹换成 Bullet_Power（更快、更大、伤害 2）。
 *
 * 同时覆盖 createBullet() 和 fire()：
 *   - 对外层是连发（DcrRapid）时，连发会调用 createBullet()，拿到强化弹；
 *   - 对普通开火，fire() 先让内层正常开一枪，再把子弹替换成强化弹。
 * 这样不管装饰顺序怎么套，都能打出强化弹。
 */
public class DcrPower extends Decorator {

	public DcrPower(Spirite s) {
		super(s);
	}

	@Override
	public Bullet createBullet() {
		Bullet b = new Bullet_Power(spirite.getX(), spirite.getY());
		b.setDir(spirite.getDir());
		return b;
	}

	@Override
	public Bullet fire() {
		Bullet b = spirite.fire();
		if (b == null) {
			return null;
		}
		Bullet power = new Bullet_Power(b.getX(), b.getY());
		power.setDir(b.getDir());
		return power;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		// 炮口火光
		int cx = getX() + 17;
		int cy = getY() + 17;
		g.setColor(new Color(255, 150, 60, 190));
		switch (getDir()) {
			case UP:    g.fillOval(cx - 4, cy - 26, 8, 8); break;
			case DOWN:  g.fillOval(cx - 4, cy + 18, 8, 8); break;
			case LEFT:  g.fillOval(cx - 26, cy - 4, 8, 8); break;
			case RIGHT: g.fillOval(cx + 18, cy - 4, 8, 8); break;
		}
	}

	@Override
	public String getBuffName() { return "强化弹"; }

	@Override
	public int getIconCol() { return Icons.POWER_COL; }

	@Override
	public int getIconRow() { return Icons.POWER_ROW; }
}
