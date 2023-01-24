public class Employee extends StaffMember {
    String socialSecurityNumber;
    double payRate;

    @Override
    public String toString() {
        return "Name: " + name + " / " + "Address: " + address + " / " + "Phone: " + phone + " / " + "Social Security Number: " + socialSecurityNumber;
    }

    public Employee(String name, String address, String phone, String socialSecurityNumber, double payRate) {
        super(name, address, phone);
        this.socialSecurityNumber = socialSecurityNumber;
        this.payRate = payRate;

    }

    @Override
    public double calculatePayment() {
        return payRate;
    }
}
