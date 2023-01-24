public class PlaneSeat extends Seat {

    int baggageLimit;

    public PlaneSeat(String label) {
        this.label = label;
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public String toString() {
        return getLabel();
    }

    public int getBaggageLimit() {
        return baggageLimit;
    }

    public void setBaggageLimit(int baggageLimit) {
        this.baggageLimit = baggageLimit;
    }

    @Override
    public String getSummary() {
        return "PlaneSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Baggage Limit: " + getBaggageLimit() + " kg - Booked: " + isBooked() + "\n";
    }
}
