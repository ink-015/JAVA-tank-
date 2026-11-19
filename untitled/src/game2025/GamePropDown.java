package game2025;

import java.awt.*;

public class GamePropDown extends Gameprop{
    public GamePropDown(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(Resourse.getInstance().getImg(), x, y, x+34, y+34,
                34*14    , 34*6,
                34*15 , 34*7, null);
    }


}
