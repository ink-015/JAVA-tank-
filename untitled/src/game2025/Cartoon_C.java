package game2025;

import java.awt.Graphics;

public class Cartoon_C extends Cartoon{	

	public Cartoon_C(int ax, int ay) {
		super(ax, ay,18);
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		int tempf = frame % 3;
		if(frame >=6)
			g.drawImage(Resourse.getInstance().getImg(), x, y, x+34, y+34,
					(13+tempf)*34 , 34*7, 
					(14+tempf)*34 , 34*8, null);
		frame++;
	}
}
