public class BusSeat extends Seat {

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    // BusSeat class should inherit from Seat abstract class
    char gender;

    public BusSeat(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public String getSummary() {
        String s = "BusSeat - Label: " + getLabel() + " - Price: " + getPrice() + " TL - Booked: " + isBooked();
        if (isBooked())
            s = s + " (" + getGender() + ")\n";
        else
            s = s + "\n";
        return s;
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }
}
