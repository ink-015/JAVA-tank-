package game2025;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Cartoon extends VisibleItem{
	private IFinish listener;
	protected int frame;
	protected int frameCount;

	 public Cartoon(int ax,int ay,int fCount) {
		 super(ax, ay);
		 frameCount = fCount;
	 }
//		 File f = new File("insect_sprite.png");
//
//		 try {
//			img = ImageIO.read(f);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	 }
	public void addListener(IFinish lsn) {
		listener = lsn;
	}
	public abstract void draw(Graphics g);
		
	
	public boolean isFinish() {
		if(frame>=frameCount) {
			if(listener != null)
				listener.doFinish();
			return true;
		}
		else return false;
	}

}
