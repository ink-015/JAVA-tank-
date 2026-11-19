package game2025;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements KeyListener{
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;
	private Image offScreenImage = null;
	private Graphics gOffScreen = null;
	
	private int[][] pos = {{100,50},{300,50},{500,50}};
	private ArrayList<NPCTank> npcTanks = new ArrayList<NPCTank>();
	private Tank playerTank = new Tank(WIDTH/2,HEIGHT-50);

	private ArrayList<Bullet> playerBullets = new ArrayList<Bullet>();
	private ArrayList<Bullet> npcBullets = new ArrayList<Bullet>();
	private ArrayList<Cartoon> cartoons = new ArrayList<Cartoon>();;

	private Map map;
	public GameFrame() {
		
		super("Tank Game");		
		setSize(WIDTH,HEIGHT);//���ô���Ŀ�͸�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		
		map = new Map();
		try {
			map.loadData("map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setVisible(true);
		
		FrameThread customThread = new FrameThread();//�½��̶߳���
		customThread.start();//�����Զ����߳�	

	}	

	private class FrameThread extends Thread {
		
		public void run(){
			while(true) {
				Random rnd = new Random();
				if(NPCTank.getCount() < 50)
					if(rnd.nextInt()%10 ==1) {
						int p = rnd.nextInt()%3;
						p = Math.abs(p);
						NPCTank t = new NPCTank(pos[p][0],pos[p][1]);
						t.setVisibleItem(playerTank);
						t.setDir(DIR.DOWN);
						t.setStrategy(new RandomMoving());
						npcTanks.add(t);
					}
				
				if((rnd.nextInt() % 10 == 1) && (npcTanks.size()>0)) {
					int index = rnd.nextInt() %	npcTanks.size();
					index = Math.abs(index);
					Bullet b = npcTanks.get(index).fire();
					if(b != null)
						npcBullets.add(b);
				}
				// ����NPC�ڵ�
				for(int i=npcBullets.size()-1; i>=0 ;--i) {
					Bullet b = npcBullets.get(i);
					b.move();
					if(b.isHitTank(playerTank)) {
						cartoons.add(new Cartoon_E(playerTank.getX(),playerTank.getY()));
						Cartoon_C cartoon = new Cartoon_C(WIDTH/2,HEIGHT-50);
						cartoon.addListener(new IFinish() {
							@Override
							public void doFinish() {
								// TODO Auto-generated method stub
								playerTank.setX(WIDTH/2);
								playerTank.setY(HEIGHT-50);
								playerTank.setDir(DIR.UP);
							}							
						});
						cartoons.add(cartoon);
						playerTank.setX(10000);
						npcBullets.remove(i);
					}
				}
				

				for(NPCTank tank : npcTanks) {
					tank.move();
					if(Resourse.getInstance().getDis(tank,playerTank)<100){
						tank.setStrategy(new FollowMoving());
					}
				}
				
				playerTank.move();
//				for(Bullet b : playerBullets) {
//					if(b.getX()<0 || b.getX()>WIDTH ||
//							b.getY()<0 || b.getY() > HEIGHT)
//						playerBullets.remove(b);
//					else
//						b.move();
//				}
//				System.out.println(playerBullets.size());
				for(int i= playerBullets.size()-1; i>=0 ;--i) {
					Bullet b = playerBullets.get(i);					
					b.move();
					if(b.isOutOfBounds())
						playerBullets.remove(b);
					else {
						for(int j= npcTanks.size()-1;j>=0;--j) {
							NPCTank t = npcTanks.get(j);
							if(b.isHitTank(t)) {
								cartoons.add( new Cartoon_E(t.getX(),t.getY()));
								npcTanks.remove(j);
								playerBullets.remove(i);
								break;
							}
						}
					}				
				}
//				
//				Iterator<Bullet> itr;
//				itr = playerBullets.iterator();
//				while(itr.hasNext()) {
//					Bullet b = itr.next();
//					b.move();
//				}
								
				repaint();
				try {
					sleep(100);//�߳�ֹͣ100����
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}
	
	public void paint(Graphics g) {			
		if(offScreenImage == null) {
		    offScreenImage = this.createImage(WIDTH, HEIGHT);
	        gOffScreen = offScreenImage.getGraphics();  
		}	   
        //Color c = gOffScreen.getColor();  
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);  
        //gOffScreen.setColor(c);  
        map.draw(gOffScreen);
        
        playerTank.draw(gOffScreen);
		for(NPCTank npcTank:npcTanks) {
			npcTank.draw(gOffScreen);
			
		}	
		for(Bullet b : playerBullets)
			b.draw(gOffScreen);
		for(Bullet b : npcBullets)
			b.draw(gOffScreen);
		
		for(int i=cartoons.size()-1;i>=0;--i) {
			Cartoon cartoon = cartoons.get(i);
			cartoon.draw(gOffScreen);
			if(cartoon.isFinish())
				cartoons.remove(i);
		}
	
		
        //���ڶ�����е�����һ����ȫ�����Ƶ���Ļ��
        g.drawImage(offScreenImage, 0, 0, null); 
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();	
		switch(key) {
		case KeyEvent.VK_UP:    playerTank.setDir(DIR.UP);break;
		case KeyEvent.VK_RIGHT:	playerTank.setDir(DIR.RIGHT);break;
		case KeyEvent.VK_DOWN:  playerTank.setDir(DIR.DOWN);break;
		case KeyEvent.VK_LEFT:  playerTank.setDir(DIR.LEFT);break;	
		case KeyEvent.VK_SPACE: 			
				Bullet b = playerTank.fire();
				if(b != null)
					playerBullets.add(b);		
			
			break;
		}
		
	
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}




//
//public class GameFrame extends JFrame{
//	 private int[] x= {50,100,150,200};
//	 private int[] y= {100,150,100,200};
//	 private final int WIDTH = 20;
//	 private final int HEIGHT = 20;
//	 private int[] dir= {0,1,2,3}; // 0 up 1 right 2 down 3 left
//	
//	public GameFrame() {
//		super("Tank Game");		
//		setSize(300,300);//���ô���Ŀ�͸�
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setVisible(true);
//		
//		FrameThread customThread = new FrameThread();//�½��̶߳���
//		customThread.start();//�����Զ����߳�
//		
////		Thread customThread1 = new Thread(new DemoThread());//�½��̶߳���
////		customThread1.start();//�����Զ����߳�
//	}	
//
//	private class FrameThread extends Thread{		
//		public void run(){
//			while(true) {
//				for(int j=0;j<x.length;++j) {
//					switch(dir[j]) {
//					case 0: y[j] -=2; break;
//					case 1: x[j] += 2;break;
//					case 2: y[j] +=2; break;
//					case 3: x[j] -= 2;break;
//					}
//				}
//					
//					repaint();
//					try {
//						sleep(100);//�߳�ֹͣ100����
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}				
//			}
//		}
//	}
//	
////	private class DemoThread implements Runnable{
////
////		@Override
////		public void run() {
////			while(true) {
////				y += 2;
////				repaint();
////				try {
////					Thread.sleep(100);//�߳�ֹͣ100����
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}		
////			
////		}
////			
////		}
////		
////	}
//	
//	public void paint(Graphics g) {
//		g.fillRect(0, 0, 500, 300);
//
//		  g.setColor(Color.RED);//���û����ϵ���ɫΪ̹����ɫ
//
//		  for(int i=0;i<x.length;++i) {
//			//��������̹�˼�
//		   g.drawRect(x[i]-WIDTH/2, y[i]-HEIGHT/2, WIDTH, HEIGHT);
//
//		   //��Բ����������̹�˼�С2������
//		   g.drawOval(x[i]-WIDTH/2+2, y[i]-HEIGHT/2+2, WIDTH-4, HEIGHT-4);
//
//		   switch(dir[i]) {
//		   case 0: 
//			   g.drawRect(x[i]-1, y[i]-28, 2, 28);//���������ڹܣ���2����28		 
//			   g.drawRect(x[i]-14, y[i]-12, 4, 24);  //����߳������Ĵ�����4����24
//			   g.drawRect(x[i]+10, y[i]-12, 4, 24); //���ұ߳������Ĵ�����4����24
//			   break;
//		   case 1: 
//			   g.drawRect(x[i], y[i]-1, 28, 2);//���������ڹܣ���2����28		 
//			   g.drawRect(x[i]-14, y[i]-12, 24, 4);  //����߳������Ĵ�����4����24
//			   g.drawRect(x[i]-14, y[i]+8, 24, 4); //���ұ߳������Ĵ�����4����24
//			   break;
//		   case 2:
//			   g.drawRect(x[i]-1, y[i], 2, 28);//���������ڹܣ���2����28		 
//			   g.drawRect(x[i]-14, y[i]-12, 4, 24);  //����߳������Ĵ�����4����24
//			   g.drawRect(x[i]+10, y[i]-12, 4, 24); //���ұ߳������Ĵ�����4����24
//			   break;
//		   case 3: 
//			   g.drawRect(x[i]-28, y[i]-1, 28, 2);//���������ڹܣ���2����28		 
//			   g.drawRect(x[i]-14, y[i]-12, 24, 4);  //����߳������Ĵ�����4����24
//			   g.drawRect(x[i]-14, y[i]+8, 24, 4); //���ұ߳������Ĵ�����4����24
//			   break;
//		   }
//		}
//	}
//}
