package GameWar;

import java.awt.*;

public class Cartoon_R extends Cartoon_E{
    private IFinish listener;//添加动画监听器，监听到结束的时候重生

    public Cartoon_R(int ax,int ay){
        super(ax, ay);
    }

    public void setListener(IFinish lsn){
        listener=lsn;
    }

    @Override
    public void draw(Graphics g) {
        int tempf=frame%3;
        if(frame>=6)
        g.drawImage(img,x,y,x+34,y+34,
                (13+tempf)*34,34*7,
                (14+tempf)*34,34*8,null);
        frame++;
    }

    @Override
    public boolean isFinish() {
        if(frame>=18){
            if (listener!=null)
              listener.doFinish();
            return true;}
        else return false;
    }
}
