package game2025;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends Spirite{	
	 public Tank(int ax, int ay) {
		 super(ax,ay);
	 }

	 public void draw(Graphics g) {	
		g.drawImage(Resourse.getImg(), x, y, x+34, y+34,
				68*dir.ordinal()+flag*34    , 34, 
				68*dir.ordinal()+flag*34+34 , 68, null);
		flag++;
		flag = flag==2?0:1;
	 }

}
