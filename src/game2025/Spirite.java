package game2025;

import java.awt.Graphics;

public abstract class Spirite extends VisibleItem{
	

	 protected final int WIDTH = 20;
	 protected final int HEIGHT = 20;
	 protected DIR dir; // 0 up 1 right 2 down 3 left

	 protected int flag=0;
	 protected int v=0;
	 private int coolTime=0;
	 public Spirite(int ax, int ay) {
		 super(ax,ay);
		 v = 2;
		 dir = DIR.UP;
	 }
	 public void setDir(DIR adir) { dir = adir;}
	 public int getX() {return x;}
	 public int getY() {return y;}
	 public void setX(int ax) {x = ax;}
	 public void setY(int ay) {y = ay;}
	 public void move() {
		switch(dir) {
		case UP: 
			if(y>34) y -= v ;
			else dir = DIR.DOWN; 
			break;
		case RIGHT: 
			if(x < GameFrame.WIDTH-34) x += v;
			else dir=DIR.LEFT;
			break;
		case DOWN: 
			if(y<GameFrame.HEIGHT-34) y += v; 
			else dir=DIR.UP;
			break;
		case LEFT: 
			if(x > 0) x -= v;
			else dir = DIR.RIGHT;
			break;
		}
		coolTime++;
		
	 }
	
	 public abstract void draw(Graphics g);
	 
	 public Bullet fire() {
		 Bullet b=null;
		 if(coolTime>10) {
			 b = new Bullet_A(x,y);
			 b.setDir(dir);
			 coolTime = 0;			
		 }
		 return b;
	 }
	
}
