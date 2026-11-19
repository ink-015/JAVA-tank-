package game2025;

import java.awt.*;

public abstract class Decorator extends Spirite{
    protected Spirite spirite;
    public Decorator(Spirite s,int ax,int ay){
        super(ax,ay);
        spirite=s;
    }
    @Override
    public void  setDir(DIR adir){spirite.setDir(adir);}
    public int getX(){return spirite.getX();}
    public int getY(){return spirite.getY();}
    public void setX(int ax) {spirite.setX(ax);}
    public void setY(int ay) {spirite.setY(ay);}
    public void move(){spirite.move();}
    public void draw(Graphics g){spirite.draw(g);}
    @Override
    public Bullet fire() {return spirite.fire();  }
}
