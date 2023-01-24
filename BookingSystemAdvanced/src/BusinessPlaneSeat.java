public class BusinessPlaneSeat extends PlaneSeat {
    
    public BusinessPlaneSeat(String label) {
        super(label);
    }

    @Override
    public int getPrice() {
        return (int) (super.getPrice() * 1.8);
    }

    @Override
    public int getBaggageLimit() {
        return super.getBaggageLimit() * 2;
    }

    @Override
    public String toString() {
        return "*" + getLabel();
    }

    @Override
    public String getSummary() {
        return "BusinessPlaneSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Baggage Limit: " + getBaggageLimit() + " kg - Booked: " + isBooked() + "\n";
    }
}
