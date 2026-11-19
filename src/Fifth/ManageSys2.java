package Fifth;

public class ManageSys2 {
    private Vehicle[] vehicles = new Vehicle[15];

    public ManageSys2() {
        vehicles[0] = new Car();
        vehicles[1] = new Car();
        vehicles[2] = new Car();

        vehicles[3] = new Ship();
        vehicles[4] = new Ship();
        vehicles[5] = new Ship();
    }

    public void getDistance() {
        for(Vehicle vehicle : vehicles) {
            if(vehicle != null)
                vehicle.move(3);
        }
    }

}
