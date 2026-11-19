package FoolsDay;
import Fifth.Vehicle;

public final class Car extends Vehicle {

    public Car() {
        super(60);
    }

    public void move(int hour) {
        System.out.println("car:"+hour*v);
    }

}