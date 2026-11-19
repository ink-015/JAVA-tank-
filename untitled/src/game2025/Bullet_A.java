package game2025;

import java.awt.Graphics;

public class Bullet_A extends Bullet{

	 public Bullet_A(int ax, int ay) {
		super(ax, ay);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		g.drawImage(Resourse.getInstance().getImg(), x+11, y+11,
				x + 21,    y + 21,
				30*34,     6*34,
				31*34,     7*34, null);
			
		 }
}
