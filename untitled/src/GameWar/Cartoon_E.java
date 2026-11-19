package GameWar;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cartoon_E {
    protected int x;
    protected int y;
    protected int frame;
    protected Image img;

    public Cartoon_E(int ax,int ay) {
        x = ax;
        y = ay;

        File f = new File("insect_sprite.png");

        try {
            img = ImageIO.read(f);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void draw(Graphics g) {
        g.drawImage(img, x, y, x+34, y+34,
                (20+frame)*34    , 34*4,
                (21+frame)*34 , 34*5, null);
        frame++;
    }

    public boolean isFinish() {
        if(frame>=3) return true;
        else return false;
    }

}

