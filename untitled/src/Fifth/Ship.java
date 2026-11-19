package Fifth;
//
//public class Ship {
//    private int v;
//    public Ship(){
//    v=80;
//    }
//    public int move(int hour){
//    return hour*v;
//    }
//}
public class Ship extends Vehicle{

    public Ship() {
        super(30);
    }

    public void move(int hour) {
        System.out.println("ship:"+hour*v);
    }
}
