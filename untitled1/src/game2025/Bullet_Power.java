package game2025;

import java.awt.Graphics;

/**
 * 强化子弹（DcrPower 装饰器制造的子弹）：更快、更粗、一次掉两点血。
 */
public class Bullet_Power extends Bullet {

	public Bullet_Power(int ax, int ay) {
		super(ax, ay);
		v = 10;
		damage = 2;
		hitRadius = 34;
	}

	@Override
	public void draw(Graphics g) {
		// 画得比普通子弹大一圈，中心对齐
		g.drawImage(Resourse.getImg(), x-4, y-4, x+38, y+38,
				5*34    , 6*34,
				6*34 , 7*34, null);
		g.setColor(new java.awt.Color(255, 200, 90, 160));
		g.drawOval(x-2, y-2, 37, 37);
	}
}
