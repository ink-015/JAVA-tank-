package game2025;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Resourse {
    private static Image img;
//    static {
//        File f = new File("insect_sprite.png");
//
//        try {
//            img = ImageIO.read(f);
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static Image getImg() {
//        return img;
//    }
//    private Resourse(){}
//}
////设计一个类专门加载这个图片，然后别的类调用，以避免每次都要加载图片导致的内存占用；但是程序一开始就加载了
////体现了专业性：一个代码只干他负责的部分的事情
////静态变量属于一个类

//单例模式：只能创建一次，且只在需要的时候加载
    private static Resourse instance=null;
    private Resourse(){
        File f = new File("insect_sprite.png");

        try {
            img = ImageIO.read(f);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Resourse getInstance(){
    if(instance==null){
        instance=new Resourse();
    }
    return instance;
    }

    public static Image getImg() {
        return img;
    }

    public int getDis(VisibleItem item1,VisibleItem item2){
        int d1= item1.getX()-item2.getX();
        d1=d1*d1;
        int d2= item1.getY()-item2.getY();
        d2=d2*d2;
        return (int) Math.sqrt(d1+d2);
    }
}
