import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    String identifier;
    int capacity;
    String model;
    List<Seat> seatList = new ArrayList<>();

    public abstract void printSeatsTable();

    public void printSeatSummaries() {
        System.out.print("### START OF SEAT SUMMARIES ###\n" + this);
        for (Seat seat : seatList) {
            System.out.print(seat.getSummary());
        }
        System.out.println("### END OF SEAT SUMMARIES ###");
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

}
