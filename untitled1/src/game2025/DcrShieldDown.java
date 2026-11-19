package game2025;

import java.awt.*;

/**
 * 最早写的护盾装饰器之一：在坦克下方画一条红条。
 */
public class DcrShieldDown extends Decorator {
    public DcrShieldDown(Spirite s){
        super(s);
    }
    @Override
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.red);
        g.fillRect(spirite.getX(),spirite.getY()+37,34,2);
    }
}
