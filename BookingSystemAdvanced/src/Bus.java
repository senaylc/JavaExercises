import java.util.ArrayList;

public class Bus extends Vehicle {
    // Bus class should inherit from Vehicle abstract class and override its abstract methods
    static ArrayList<BusSeat> busSeats = new ArrayList<>();

    static ArrayList<ArrayList<BusSeat>> twoPairBusSeats = new ArrayList<>();


    public Bus(String model, String identifier, int capacity) {
        this.model = model;
        this.identifier = identifier;
        this.capacity = capacity;
        setBusSeats();
        pairSeats(busSeats, twoPairBusSeats);
    }
    public void setBusSeats() {
        busSeats.clear();
        for (int i = 1; i < capacity + 1; i++) {
            busSeats.add(new BusSeat(String.valueOf(i)));
        }
        seatList.addAll(busSeats);
    }

    public void pairSeats(ArrayList<BusSeat> busSeats, ArrayList<ArrayList<BusSeat>> twoPairBusSeats) {
        for (int i = 1; i < busSeats.size(); i = i + 2) {
            ArrayList<BusSeat> pairs = new ArrayList<>();
            pairs.add(busSeats.get(i - 1));
            pairs.add(busSeats.get(i));
            twoPairBusSeats.add(pairs);
        }
    }


    public void bookSeat(String seatLabel,char gender) {

        int counter = 0;
        for (BusSeat bs : busSeats) {
            if (bs.getLabel().equals(seatLabel)) {
                if (bs.isBooked()) {
                    System.out.print(seatLabel + " cannot be booked, it's already booked.\n");
                    counter += 1;
                }
                if (!bs.isBooked()) {
                    for (ArrayList<BusSeat> arrayBus : twoPairBusSeats) {
                        if (arrayBus.contains(bs)) {
                            int index = arrayBus.indexOf(bs);
                            arrayBus.remove(bs);
                            for (BusSeat b : arrayBus) {
                                if (!b.isBooked() || b.getGender() == gender) {
                                    System.out.print(seatLabel + " booked.\n");
                                    bs.setGender(gender);
                                    bs.setBooked(true);
                                    counter += 1;
                                } else {
                                    System.out.print(bs.getLabel() + " cannot be booked because neighbor seat was booked by different gender person.\n");
                                    counter += 1;
                                }
                            }
                            arrayBus.add(index, bs);
                        }
                    }
                }
            }
        }
        if (counter == 0) System.out.print("No such seat exists.\n");
    }

    @Override
    public String toString() {
        return "Bus (" + identifier + "), model: " + model + ", capacity: " + capacity + "\n";
    }

    @Override
    public void printSeatsTable() {

        int count = 0;
        for (int i = 0; i < capacity; i++) {
            System.out.print(busSeats.get(i).toString() + "\t" + "[");
            if (busSeats.get(i).isBooked()) {
                System.out.print(busSeats.get(i).getGender());
            } else System.out.print(" ");
            System.out.print("]");
            count++;
            if ((count % 4 != 0) && (count % 2 == 0)) System.out.print("\t\t\t");
            if ((count % 2 != 0)) System.out.print("\t");
            if (count % 4 == 0) System.out.print("\n");
        }
        System.out.print("### END OF BUS INFO ###\n\n");
    }

}
