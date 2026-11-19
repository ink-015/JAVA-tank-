package third;

//类，相当于结构体，但是可以包含方法，函数，等等
//struct STU{
//int age;
//char name;
//}
//STU x=zhangsan;
//println("%n,x.age");

//public class ClassA {
//    public int add(int x,int y){
//        return x+y;
//    }
//    public int minus(int x,int y){
//        return x-y;
//    }
//}在thirdwork中的main3中使用
public class ClassA {
//    public int x;//可以记录下被运算的数
//    public int y;
    private int x;
    private int y;//x,y不能被查看
    public void setX(int x) {
//        if(x>10);
//        return;可以加一些条件和权限来验证
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int add(){
        return x+y;
    }
    public int minus(){
        return x-y;
    }
}
