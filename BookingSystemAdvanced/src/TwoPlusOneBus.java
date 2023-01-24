import java.util.ArrayList;

public class TwoPlusOneBus extends Bus {
   
    static ArrayList<BusinessBusSeat> businessBusSeats = new ArrayList<>();

    public TwoPlusOneBus(String model, String identifier, int capacity) {
        super(model, identifier, capacity);

    }

    public void setBusSeats() {
        busSeats.clear();
        businessBusSeats.clear();
        for (int i = 3; i < capacity + 1; i = i + 3) {
            BusinessBusSeat bs = new BusinessBusSeat(String.valueOf(i - 2));
            businessBusSeats.add(bs);
            busSeats.add(bs);
            busSeats.add(new BusSeat(String.valueOf(i - 1)));
            busSeats.add(new BusSeat(String.valueOf(i)));
        }
        seatList.addAll(busSeats);
    }

    public void pairSeats(ArrayList<BusSeat> busSeats, ArrayList<ArrayList<BusSeat>> twoPairBusSeats) {
        for (int i = 2; i < busSeats.size(); i = i + 3) {
            ArrayList<BusSeat> pairs = new ArrayList<>();
            pairs.add(busSeats.get(i - 1));
            pairs.add(busSeats.get(i));
            twoPairBusSeats.add(pairs);
        }
    }

    @Override
    public void bookSeat(String seatLabel, char gender) {
        int counter = 0;
        for (BusSeat bs : busSeats) {
            if (bs.getLabel().equals(seatLabel)) {
                if (bs.isBooked()) {
                    System.out.print(seatLabel + " cannot be booked, it's already booked.\n");
                    counter += 1;
                } else if (!bs.isBooked() && !(bs instanceof BusinessBusSeat)) {
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

                } else {
                    System.out.print(seatLabel + " booked.\n");
                    bs.setGender(gender);
                    bs.setBooked(true);
                    counter += 1;
                }
            }
        }
        if (counter == 0) System.out.print("No such seat exists.\n");
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
            if (count == 1) System.out.print("\t\t\t");
            if (count == 2) System.out.print("\t");
            if (count == 3) {
                System.out.print("\n");
                count = 0;
            }
        }
        System.out.print("### END OF BUS INFO ###\n\n");
    }

    @Override
    public String toString() {
        return "TwoPlusOneBus (" + identifier + "), model: " + model + ", capacity: " + capacity + "\n";
    }
}
