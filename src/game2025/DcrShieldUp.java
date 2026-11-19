package game2025;

import java.awt.*;

public class DcrShieldUp extends Decorator{
    public DcrShieldUp(Spirite s,int ax,int ay){
        super(s,ax,ay);
    }
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.red);
        g.fillRect(spirite.getX(),spirite.getY(),34,2);
    }
}