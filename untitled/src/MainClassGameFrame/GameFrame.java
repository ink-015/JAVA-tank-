package MainClassGameFrame;
import java.awt.Color;
import java.awt.Graphics;
//import java.util.Scanner;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
//    int x = 30;
//    int y = 50;//像素点，左上角为00，从上向下从左向右，
    private int[] x={120,300,150,220};
    private int[] y={100,170,350,260};
    private int i=0;
//    private int m =4;
    private int[] dir={0,1,2,3};
//    Scanner in=new Scanner(System.in);
//    int num=in.nextInt();
    public GameFrame() {
        super("Tank Game");
        setSize(1600,1600);//设置窗体的宽和高
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);//窗体部分

        FrameThread customThread = new FrameThread();//新建线程对象
        customThread.start();//启动自定义线程
    }
    private class FrameThread extends Thread {
        public void run() {
            while (true) {
                for (int j = 0; j < dir.length; ++j) {
                    switch (dir[j]) {
                        case 0:
                            y[j] += 5;
                            break;
                        case 1:
                            y[j] -= 5;
                            break;
                        case 2:
                            x[j] += 5;
                            break;
                        case 3:
                            x[j] -= 5;
                            break;
                    }
//                x -=60* m;//在重复画的时候x加了四次60
                    repaint();
                    try {
                        sleep(100);//线程停止200毫秒
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1600, 1600);


        for(i=0; i< x.length; i++){
        g.setColor(Color.RED);
        g.drawRect(x[i]-20, y[i]-20, 4, 40);//从上往下，从左往右画的
        g.drawRect(x[i]+16, y[i]-20, 4, 40);//两边履带

        g.drawRect(x[i]-16, y[i]-16, 32, 32);
        g.drawOval(x[i]-14, y[i]-14, 28, 28);//主体和塔楼

        g.drawRect(x[i]-1, y[i], 2, 40);//炮管
//        x=x+60;//平移60像素再画一个
    }
  }
}

