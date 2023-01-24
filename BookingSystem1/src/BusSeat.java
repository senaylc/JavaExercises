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

}
