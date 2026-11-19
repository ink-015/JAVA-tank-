package GameWar;

import java.awt.*;

public class BlockStone extends Block{
    public BlockStone(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(img, x, y, x+17, y+17,      0 , 6*34,17 , 6*34+17, null);
        g.drawImage(img, x+17, y, x+34, y+17,   0 , 6*34,17 , 6*34+17, null);
        g.drawImage(img, x, y+17, x+17, y+34,   0 , 6*34,17 , 6*34+17, null);
        g.drawImage(img, x+17, y+17, x+34, y+34,0 , 6*34,17 , 6*34+17, null);
    }

}
