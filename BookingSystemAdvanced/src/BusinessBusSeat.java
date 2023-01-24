public class BusinessBusSeat extends BusSeat {

    public BusinessBusSeat(String label) {
        super(label);
    }

    @Override
    public int getPrice() {
        return (int) ((super.getPrice()) * 1.3);
    }

    @Override
    public String toString() {
        return "*"+getLabel();
    }

    @Override
    public String getSummary() {
        String s= "BusinessBusSeat - Label: "+getLabel()+" - Price: "+getPrice()+" TL - Booked: "+isBooked();
        if (isBooked())
            s=s+" ("+getGender()+")\n";
        else
            s=s+"\n";
        return s;
    }
}

