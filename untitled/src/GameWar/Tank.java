package GameWar;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Tank {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    protected int x;
    protected int y;
    protected DIR dir;
    protected int coolTime=0;
    protected final int WIDTH = 20;
    protected final int HEIGHT = 20;// 0 up 1 right 2 down 3 left
    protected int flag = 0;
    protected int v = 2;
    protected Image img;

    public Tank(int ax, int ay) {
        x = ax;
        y = ay;
        dir=DIR.UP;
        String imagePath = "D:\\课业\\编程课\\java\\上课代码\\java\\example2025-3\\insect_sprite.png";
        File f = new File(imagePath);
        try {
            img = ImageIO.read(f);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setDir(DIR adir) {
        dir = adir;
    }
    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int ax) {x=ax;}
    public void setY(int ay) {y=ay;}
    public void move() {
        switch (dir.ordinal()) {
            case UP:
                if(y>34)
                y -= v;
                else dir = DIR.DOWN;
                break;
            case RIGHT:
                if(x<GameFrame.WIDTH-34)
                  x += v;
                else dir = DIR.LEFT;
                break;
            case DOWN:
                if(y<GameFrame.HEIGHT-34)
                y += v;
                else dir = DIR.UP;
                break;
            case LEFT:
                if(x>0)
                x -= v;
                else dir = DIR.RIGHT;
                break;
        }
        coolTime++;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y,
                x + 34, y + 34,
                68 * dir.ordinal() + flag * 34, 34,
                68 * dir.ordinal() + flag * 34 + 34, 68, null);
//        int m=x;
//        int n=y;
//        switch(dir) {
//            case 0:
//                if(m==x&&n==y) {
//                    g.drawImage(img, x, y, x + 34, y + 34, 0, 0, 34, 34, null);
//                }
//                else{
//                    g.drawImage(img, x, y, x+34, y+34, 34, 0, 68, 34, null);
//                    n=y-4;
//                }
//                break;
//            case 1:
//                if(m==x&&n==y)
//                g.drawImage(img, x, y, x+34, y+34, 68, 0, 68+34, 34, null);
//                else{
//                    g.drawImage(img, x, y, x+34, y+34, 68+34, 0, 68*2, 34, null);
//                    m=x+4;
//                }
//                break;
//            case 2:
//                if(m==x&&n==y)
//                g.drawImage(img, x, y, x+34, y+32, 68*2, 0, 68*2+34, 34, null);
//                else{
//                    g.drawImage(img, x, y, x+34, y+34, 68*2+34, 0, 68*3, 34, null);
//                    n=y+4;
//                }
//                break;
//            case 3:
//                if(m==x&&n==y)
//                g.drawImage(img, x, y, x+34, y+34, 68*3, 0, 68*3+34, 34, null);
//                else{
//                g.drawImage(img, x, y, x+34, y+34, 68*3+34, 0, 68*4, 34, null);
//                m=x-4;
//            }
//                break;
//        }
        flag++;
        flag = flag == 2 ? 0 : 1;
    }
    public Bullet fire(){
        Bullet b=null;
        if(coolTime>10) {
            b = new Bullet(x, y);
            b.setDir(dir);
            coolTime=0;
        }
        return b;

    }

}
//    public void draw1(Graphics g) {
//        g.drawRect(x-WIDTH/2, y-HEIGHT/2, WIDTH, HEIGHT);
//        g.drawOval(x-WIDTH/2+2, y-HEIGHT/2+2, WIDTH-4, HEIGHT-4);
//        switch(dir) {
//            case 0:
//                g.drawRect(x-1, y-28, 2, 28);
//                g.drawRect(x-14, y-12, 4, 24);
//                g.drawRect(x+10, y-12, 4, 24);
//                break;
//            case 1:
//                g.drawRect(x, y-1, 28, 2);
//                g.drawRect(x-14, y-12, 24, 4);
//                g.drawRect(x-14, y+8, 24, 4);
//                break;
//            case 2:
//                g.drawRect(x-1, y, 2, 28);
//                g.drawRect(x-14, y-12, 4, 24);
//                g.drawRect(x+10, y-12, 4, 24);
//
//                break;
//            case 3:
//                g.drawRect(x-28, y-1, 28, 2);
//                g.drawRect(x-14, y-12, 24, 4);
//                g.drawRect(x-14, y+8, 24, 4);
//                break;
//        }
//    }
//}

