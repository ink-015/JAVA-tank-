package game2025;

import java.awt.*;

public class GamePropUp extends Gameprop{
    public GamePropUp(int x,int y){
        super(x,y);
    }
    public void draw(Graphics g){
        g.drawImage(Resourse.getInstance().getImg(),getX(),getY(),getX()+34,getY()+34,
        34*13,34*6,
        34*14,34*7,null);
    }
}
