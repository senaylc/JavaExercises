public class Command {

    public static Plane addVehiclePlane(String line) {
        String[] add = line.split(" ");
        int capacity = Integer.parseInt(add[5]);
        Plane plane = new Plane(add[3], add[4], capacity, Integer.parseInt(add[6]));
        System.out.print("Vehicle was added. ");
        System.out.print(plane);
        return plane;
    }

    public static Bus addVehicleBus(String line) {
        String[] add = line.split(" ");
        Bus bus = new Bus(add[3], add[4], Integer.parseInt(add[5]));
        System.out.print("Vehicle was added. ");
        System.out.print(bus);
        return bus;
    }
}
