package GameWar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Block {
    protected int x;
    protected int y;
    protected Image img;

    public Block(int x,int y){
        this.x=x;
        this.y=y;
        File f=new File("insect_sprite.png");
        try{
            img= ImageIO.read(f);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics g){

    }
}
