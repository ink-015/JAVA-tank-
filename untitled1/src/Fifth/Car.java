package Fifth;
//
//public class Car {
//    private int v;
//    public Car(){
//    v=60;
//    }
//    public int move(int hour){
//    return hour*v;
//    }
//}
public class Car extends Vehicle{

    public Car() {
        super(60);
    }

    public void move(int hour) {
        System.out.println("car:"+hour*v);
    }

}