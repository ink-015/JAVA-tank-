package game2025;

import java.awt.Graphics;

public class Cartoon_E extends Cartoon{
	
	 public Cartoon_E(int ax,int ay) {
		 super(ax,ay,3);
	 }
	public void draw(Graphics g) {
		g.drawImage(Resourse.getInstance().getImg(), x, y, x+34, y+34,
				(20+frame)*34    , 34*4, 
				(21+frame)*34 , 34*5, null);
		frame++;
	}

}
