import java.util.ArrayList;

public class Plane extends Vehicle {

    int businessSeatCount;

    ArrayList<PlaneSeat> allSeats = new ArrayList<>();

    ArrayList<BusinessPlaneSeat> businessSeats = new ArrayList<>();


    public Plane(String model, String identifier, int capacity, int businessSeatCount) {
        this.model = model;
        this.identifier = identifier;
        this.capacity = capacity;
        this.businessSeatCount = businessSeatCount;
        int row = capacity / 6;
        int busRow = businessSeatCount / 6;
        for (int i = 1; i < busRow + 1; i++) {
            businessSeats.add(new BusinessPlaneSeat(i + "A"));
            businessSeats.add(new BusinessPlaneSeat(i + "B"));
            businessSeats.add(new BusinessPlaneSeat(i + "C"));
            businessSeats.add(new BusinessPlaneSeat(i + "D"));
            businessSeats.add(new BusinessPlaneSeat(i + "E"));
            businessSeats.add(new BusinessPlaneSeat(i + "F"));
        }
        allSeats.addAll(businessSeats);
        for (int i = busRow + 1; i < row + 1; i++) {
            allSeats.add(new PlaneSeat(i + "A"));
            allSeats.add(new PlaneSeat(i + "B"));
            allSeats.add(new PlaneSeat(i + "C"));
            allSeats.add(new PlaneSeat(i + "D"));
            allSeats.add(new PlaneSeat(i + "E"));
            allSeats.add(new PlaneSeat(i + "F"));
        }
    }

    public void bookSeat(String seatLabel) {
        int counter = 0;
        for (PlaneSeat ps : allSeats) {
            if (ps.getLabel().equals(seatLabel)) {
                if (ps.isBooked()) {
                    System.out.print(seatLabel + " cannot be booked, it's already booked.\n");
                    counter += 1;
                }
                if (!ps.isBooked()) {
                    System.out.print(seatLabel + " booked.\n");
                    ps.setBooked(true);
                    counter += 1;
                }

            }
        }
        if (counter == 0) System.out.print("No such seat exists.\n");

    }

    @Override
    public String toString() {
        return "Plane (" + identifier + "), model: " + model + ", capacity: " + capacity + ", businessSeatCount: " + businessSeatCount + "\n"; // TODO: this should return a string representation of object
    }

    @Override
    public void printSeatsTable() {

        int count = 0;
        for (int i = 0; i < capacity; i++) {
            if (i < businessSeatCount) System.out.print("*");
            System.out.print(allSeats.get(i).getLabel() + "\t" + "[");
            if (allSeats.get(i).isBooked()) {
                System.out.print("X");
            } else System.out.print(" ");
            System.out.print("]");
            count++;
            if ((count % 6 != 0) && (count % 3 == 0)) System.out.print("\t\t\t");
            if ((count % 3 != 0)) System.out.print("\t");
            if (count % 6 == 0) System.out.print("\n");
        }
        System.out.print("### END OF PLANE INFO ###\n\n");
    }

} 
