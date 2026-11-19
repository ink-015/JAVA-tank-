package GameWar;

import java.awt.*;

public class Bullet extends Tank{
    public Bullet(int ax,int ay){
        super(ax, ay);
        v=5;
    }
    public void draw(Graphics g) {
        g.drawImage(img, x+11, y+11,
                x + 21,    y + 21,
                30*34,     6*34,
                31*34,     7*34, null);
    }
    public boolean isHitTank(Tank tank){
        double dx=Math.pow(tank.x-x,2);
        double dy=Math.pow(tank.y-y,2);
        double d=Math.sqrt(dx+dy);
        if(d<15)
            return true;
        else
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
