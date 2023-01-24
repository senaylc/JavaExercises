public class Command {

    public static Plane addVehiclePlane(String line) {
        String[] add = line.split(" ");
        int capacity = Integer.parseInt(add[5]);
        if (add[3].contains("Boeing")) {
            Plane plane = new Plane(add[3], add[4], capacity, Integer.parseInt(add[6]));
            System.out.print("Vehicle was added. ");
            System.out.print(plane);
            return plane;
        } else if (add[3].contains("Airbus")) {
            LargePlane largePlane = new LargePlane(add[3], add[4], capacity, Integer.parseInt(add[6]));
            System.out.print("Vehicle was added. ");
            System.out.print(largePlane);
            return largePlane;
        } else return null;
    }

    public static Bus addVehicleBus(String line) {
        String[] add = line.split(" ");
        if (add[3].contains("Tourismo") | add[3].contains("Fortuna")) {
            Bus bus = new Bus(add[3], add[4], Integer.parseInt(add[5]));
            System.out.print("Vehicle was added. ");
            System.out.print(bus);
            return bus;
        } else if (add[3].contains("Neoplan") | add[3].contains("Starliner")) {
            TwoPlusOneBus tbus = new TwoPlusOneBus(add[3], add[4], Integer.parseInt(add[5]));
            System.out.print("Vehicle was added. ");
            System.out.print(tbus);
            return tbus;
        } else return null;

    }
}
