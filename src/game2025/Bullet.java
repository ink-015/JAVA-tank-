package game2025;

import java.awt.Graphics;

public abstract class Bullet extends VisibleItem{
	protected DIR dir;
	protected int v;

	public Bullet(int ax, int ay) {
		super(ax,ay);
		v = 6;		
	 }
	 public void setDir(DIR adir) { dir = adir;}
	 public int getX() {return x;}
	 public int getY() {return y;}
	 public void setX(int ax) {x = ax;}
	 public void setY(int ay) {y = ay;}
	
	 public void draw(Graphics g) {	
		g.drawImage(Resourse.getInstance().getImg(), x, y, x+34, y+34,
				5*34    , 6*34, 
				6*34 , 7*34, null);
		
	 }
	public boolean isHitTank(Spirite tank) {
		double dx = Math.pow(tank.x-x,2);
		double dy = Math.pow(tank.y-y,2);
		double d = Math.sqrt(dx+dy);
		
		if(d<30)return true;
		return false;
		
	}
	
	public void move() {
		switch(dir) {
		case UP:  y -= v ;	break;
		case RIGHT: x += v;break;
		case DOWN: y += v; break;
		case LEFT: x -= v;break;
		}
	 }
	
	public boolean isOutOfBounds() {
		if( (y<=34) || (x >= GameFrame.WIDTH-34) ||
				(y>=GameFrame.HEIGHT-34) ||(x <= 0))
			return true;
		else return false;
	}
	

}
