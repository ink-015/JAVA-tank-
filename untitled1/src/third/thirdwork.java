package third;
import java.util.Scanner;
import third.ClassA;
import third.ClassB;
public class thirdwork {
    public static void main1(String[] args){
        //TODO Auto-generated.method.stub
        Scanner sc;
        sc=new Scanner(System.in);
        int x;
        int y;
        x=sc.nextInt();
        y=sc.nextInt();
        System.out.println(x+y);
    }
    public static void main2(String[] args){
        Scanner sc;//声明一个变量
        sc=new Scanner(System.in);//新建一个实例
        float x;
        float y;
        int z;
        x=sc.nextFloat();
        y=sc.nextFloat();
        z=sc.nextInt();
        System.out.println(x/y);
        System.out.println(z/0);//浮点数除0得infinity表示无穷大，整数除0报错
    }
    public static void main3(String[] args){
         ClassA cl=new ClassA();
         System.out.println(cl.add());//需要先生成一个实例，从实例调用
         System.out.println(cl.minus());//有static可以直接从类调用
    }
    public static void main4(String[] args){
        ClassA cl=new ClassA();
        cl.setX(3);
        cl.setY(2);
        System.out.println(cl.add());
        System.out.println(cl.minus());
    }
    public static void main(String[] args){
        ClassB cl;
        cl=new ClassB(3,2);
        System.out.println();
    }
}
