package GameWar;

import java.awt.*;

public class BlockGrass extends Block{
    public BlockGrass(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(img, x, y, x+34, y+34,
                4*34    , 7*34,
                5*34 , 8*34, null);
    }

}
