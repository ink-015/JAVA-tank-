package game2025;

import java.awt.*;

/**
 * 最早写的护盾装饰器之一：在坦克上方画一条红条。
 * 构造函数随 Decorator 一起改成只接收被装饰对象（与上课代码一致）。
 */
public class DcrShieldUp extends Decorator {
    public DcrShieldUp(Spirite s){
        super(s);
    }
    @Override
    public void draw(Graphics g){
        super.draw(g);
        g.setColor(Color.red);
        g.fillRect(spirite.getX(),spirite.getY(),34,2);
    }
}
