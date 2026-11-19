package game2025;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class VisibleItem {
	protected int x;
	protected int y;
	public int getX() {return x;}
	public int getY() {return y;}
//	protected static Image img;
//	static {
//		File f = new File("insect_sprite.png");
//
//		 try {
//			img = ImageIO.read(f);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
	public VisibleItem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics g);
}
