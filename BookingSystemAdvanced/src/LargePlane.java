import java.util.ArrayList;

public class LargePlane extends Plane {

    static ArrayList<BusinessPlaneSeat> businessSeats = new ArrayList<>();

    public LargePlane(String model, String identifier, int capacity, int businessSeatCount) {
        super(model, identifier, capacity, businessSeatCount);
    }

    @Override
    public void bookSeat(String seatLabel) {
        super.bookSeat(seatLabel);
    }

    @Override
    public void addSeat() {
        allSeats.clear();
        businessSeats.clear();
        int row = capacity / 8;
        int busRow = businessSeatCount / 8;
        for (int i = 1; i < busRow + 1; i++) {
            businessSeats.add(new BusinessPlaneSeat(i + "A"));
            businessSeats.add(new BusinessPlaneSeat(i + "B"));
            businessSeats.add(new BusinessPlaneSeat(i + "C"));
            businessSeats.add(new BusinessPlaneSeat(i + "D"));
            businessSeats.add(new BusinessPlaneSeat(i + "E"));
            businessSeats.add(new BusinessPlaneSeat(i + "F"));
            businessSeats.add(new BusinessPlaneSeat(i + "G"));
            businessSeats.add(new BusinessPlaneSeat(i + "H"));
        }
        allSeats.addAll(businessSeats);
        for (int i = busRow + 1; i < row + 1; i++) {
            allSeats.add(new PlaneSeat(i + "A"));
            allSeats.add(new PlaneSeat(i + "B"));
            allSeats.add(new PlaneSeat(i + "C"));
            allSeats.add(new PlaneSeat(i + "D"));
            allSeats.add(new PlaneSeat(i + "E"));
            allSeats.add(new PlaneSeat(i + "F"));
            allSeats.add(new PlaneSeat(i + "G"));
            allSeats.add(new PlaneSeat(i + "H"));
        }
        seatList.addAll(allSeats);
    }

    @Override
    public void printSeatsTable() {

        int count = 0;
        for (int i = 0; i < capacity; i++) {
            System.out.print(allSeats.get(i).toString() + "\t" + "[");
            if (allSeats.get(i).isBooked()) {
                System.out.print("X");
            } else System.out.print(" ");
            System.out.print("]");
            count++;
            if (count == 2) System.out.print("\t\t\t");
            if (count == 6) System.out.print("\t\t\t");
            if (count == 1 || count == 3 || count == 4 || count == 5 || count == 7)
                System.out.print("\t");
            if (count == 8) {
                System.out.print("\n");
                count = 0;
            }
        }
        System.out.print("### END OF PLANE INFO ###\n\n");
    }

    @Override
    public String toString() {
        return "LargePlane (" + identifier + "), model: " + model + ", capacity: " + capacity + ", businessSeatCount: " + businessSeatCount + "\n"; // TODO: this should return a string representation of object
    }

}
