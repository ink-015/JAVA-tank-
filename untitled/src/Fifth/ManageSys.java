package Fifth;

public class ManageSys {
    private Car[] cars=new Car[10];
    private Ship[] ships=new Ship[10];//这是一个数组，并非一个实例
    public ManageSys(){
        //NullPointerException"空指针异常"因为没有创建每一个car的实例
    cars[0]=new Car();
        //ok！
    cars[1]=new Car();
    cars[2]=new Car();
        ships[0]=new Ship();
        ships[1]=new Ship();
        ships[2]=new Ship();
    }
    public void getDistance(){
        for(Car car:cars){
            if(car != null)
                car.move(3);
        }
        for(Ship ship:ships){
            if(ship != null)
                ship.move(3);
        }
    }
}
