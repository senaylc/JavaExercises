public class Volunteer extends StaffMember{

    public Volunteer(String name, String address, String phone) {
        super(name, address, phone);
    }

    @Override
    public double calculatePayment() {
        return 0;
    }
    
}
