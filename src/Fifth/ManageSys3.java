package Fifth;

public class ManageSys3 {
    private IMove[] vehicles = new IMove[15];

    public ManageSys3() {
        vehicles[0] = new Car();
        vehicles[1] = new Car();
        vehicles[2] = new Car();

        vehicles[3] = new Ship();
        vehicles[4] = new Ship();
        vehicles[5] = new Ship();
        vehicles[6] = new Employee();
    }

    public void getDistance() {
        for(IMove vehicle : vehicles) {
            if(vehicle != null)
                vehicle.move(3);
        }
    }


}
