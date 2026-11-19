package GameWar;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
public class GameFrame extends JFrame implements KeyListener{
    public static final int WIDTH=600;
    public static final int HEIGHT=600;
    private Image offScreenImage = null;
    private Graphics gOffScreen = null;

    private int[][] pos={{100,50},{300,50},{500,50}};
    private ArrayList<NPCTank> npctanks = new ArrayList<NPCTank>();
    private Tank playerTank=new Tank(WIDTH/2,HEIGHT-50);

    private ArrayList<Bullet>playerBullets = new ArrayList<Bullet>();
    private ArrayList<Bullet>npcBullets = new ArrayList<Bullet>();
    private ArrayList<Cartoon_E>cartoons = new ArrayList<Cartoon_E>();

    private Map map;
    public GameFrame() {
        super("Tank Game");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);

//        tanks[0] = new NPCTank(WIDTH/3,100);
//        tanks[0].setDir(Tank.DOWN);
//
//        tanks[1] = new NPCTank(WIDTH/2,100);
//        tanks[1].setDir(Tank.DOWN);
//
//        tanks[2] = new NPCTank(WIDTH*2/3,100);
//        tanks[2].setDir(Tank.DOWN);
        map = new Map();
        try {
            map.loadData("map1.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setVisible(true);

        FrameThread customThread = new FrameThread();
        customThread.start();

    }

    private class FrameThread extends Thread{
        public void run(){
            while(true) {
                Random rnd=new Random();
                if(rnd.nextInt()%10==1){
                    int p=rnd.nextInt()%3;
                    p=Math.abs(p);
                    NPCTank t=new NPCTank(pos[p][0],pos[p][1]);
                    t.setDir(DIR.DOWN);
                    npctanks.add(t);
                }
                if((rnd.nextInt()%10==1)&&(npctanks.size()>0)){
                    int index=rnd.nextInt()%npctanks.size();
                    index=Math.abs(index);
                    Bullet b=npctanks.get(index).fire();
                    if(b!=null)
                        npcBullets.add(b);
                }
                for(int i=npcBullets.size()-1;i>=0;i--){//由于删除的影响，所以从后往前遍历更好
                    Bullet b=npcBullets.get(i);
                    b.move();
                    if(b.isHitTank(playerTank)){
                        cartoons.add(new Cartoon_E(playerTank.getX(),playerTank.getY()));
                        playerTank.setX(10000);
                        npcBullets.remove(i);
                    }
                }
                for(Tank tank : npctanks) {
                    tank.move();
//                    Random rnd2 = new Random();
//                    if(rnd.nextInt()%10==4){
//                        tank.setDir(Math.abs(rnd2.nextInt()%4));
//                    }
                }
                playerTank.move();
//                for(Bullet b:playerBullets) {
//                    if(b.getX()<0||b.getX()>WIDTH||
//                    b.getY()<0||b.getY()> HEIGHT)
//                        playerBullets.remove(b);
//                    else
//                         b.move();
//                }
//                System.out.println(playerBullets.size());//给出玩家炮弹数
                for(int i=playerBullets.size()-1;i>=0;--i){//由于删除的影响，所以从后往前遍历更好
                    Bullet b=playerBullets.get(i);
                    b.move();
                    if(b.isOutOfBounds())
                        playerBullets.remove(b);//删除之后会导致删除元素之后的链表下标发生变化，遍历的时候就会出问题
                    else {
                       for(int j=npctanks.size()-1;j>=0;--j) {
                           NPCTank t= npctanks.get(j);
                           if(b.isHitTank(t)){
                               cartoons.add(new Cartoon_E(t.getX(),t.getY()));
                               npctanks.remove(j);
                               playerBullets.remove(i);//遍历npc的炮弹
                               break;
                          }
                       }
                    }
                }//与上面的代码效果一致

//                Iterator<Bullet> itr;
//                itr = playerBullets.iterator();
//                while(itr.hasNext()){
//                    Bullet b=itr.next();
//                    b.move();
//                }迭代器类，和上面的循环效果也一样
                repaint();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    public void paint(Graphics g) {
        if(offScreenImage==null) {
            offScreenImage = this.createImage(WIDTH, HEIGHT);
            gOffScreen = offScreenImage.getGraphics();
        }
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,WIDTH,HEIGHT);
//        g.fillRect(0, 0, 500, 300);
//        g.setColor(Color.RED);
        map.draw(gOffScreen);

        playerTank.draw(gOffScreen);
        for(Tank npcTank : npctanks) {
            npcTank.draw(gOffScreen);
        }
        for(Bullet b: playerBullets )
            b.draw(gOffScreen);
        for(Bullet b: npcBullets )
            b.draw(gOffScreen);
        for(int i=cartoons.size()-1;i>=0;--i) {
          Cartoon_E cartoon=cartoons.get(i);
          cartoon.draw(gOffScreen);
          if(cartoon.isFinish())
              cartoons.remove(i);
        }
        g.drawImage(offScreenImage,0,0,null);
    }
    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e) {
        int key=e.getKeyCode();
        switch(key) {
            case KeyEvent.VK_UP:
                playerTank.setDir(DIR.UP);
                break;
            case KeyEvent.VK_RIGHT:
                playerTank.setDir(DIR.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDir(DIR.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDir(DIR.LEFT);
                break;
            case KeyEvent.VK_SPACE:
                Bullet b = playerTank.fire();
                if(b!=null)
                   playerBullets.add(b);
                break;
        }
//        for(Tank tank:tanks){
//            tank.setDir(dir);
//        }
    }
    public void keyReleased(KeyEvent e){

    }

}

