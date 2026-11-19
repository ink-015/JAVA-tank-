package game2025;

import java.awt.Graphics;
import java.util.Random;

public class NPCTank extends Spirite{
	private static int count=0;
	protected Random rnd = null;
	protected VisibleItem tank;
	private IStrategy strategy;
//	private int last;

	public NPCTank(int ax, int ay) {
		super(ax, ay);		
		v = 2;
		// TODO Auto-generated constructor stub
		rnd = new Random();
		count++;
	}
	public void setVisibleItem(VisibleItem item) {tank = item;}
	public void setStrategy(IStrategy s){strategy = s;}
	public static int  getCount() {return count;}
	 public void move() {
		 super.move();
		 if (strategy!=null&&tank!=null){
			 DIR temDir=strategy.calculateDir(x,y,tank.getX(),tank.getY());
			 if(temDir!=null)
				 setDir(temDir);
		 }
//		 last=rnd.nextInt() %10;
//		 switch(last) {
//			 case 1 :
//			 int index= Math.abs(rnd.nextInt()%4);
//			 setDir(DIR.values()[index]);
//			 break;
//
//			 case 2:
//			 if (x > tank.getX()) {x -= v;} else x += v;
//
//			 case 3:
//			 if (y > tank.getY()) { y -= v;} else y += v;
//		 }
	 }
	
	public void draw(Graphics g) {	
		g.drawImage(Resourse.getInstance().getImg(), x, y, x+34, y+34,
				68*dir.ordinal()+flag*34    , 0, 
				68*dir.ordinal()+flag*34+34 , 34, null);
		flag++;
		flag = flag==2?0:1;
	 }

}
