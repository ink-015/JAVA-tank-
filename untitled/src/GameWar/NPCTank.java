package GameWar;
import java.awt.*;
import java.util.Random;

public class NPCTank extends Tank{
    private Random rnd=null;
    public NPCTank(int ax,int ay){
        super(ax,ay);
        v=2;
        rnd=new Random();
    }
    public void move(){
        super.move();
        if(rnd.nextInt()%10==1){
            int index=Math.abs(rnd.nextInt()%4);
            setDir(DIR.values()[index]);
        }
    }
    public void draw(Graphics g) {
        g.drawImage(img, x, y,
                x + 34,           y + 34,
                68*dir.ordinal()+flag*34,    0,
                68*dir.ordinal()+flag*34+34, 34, null);
        flag++;
        flag=flag==2?0:1;
    }
}
