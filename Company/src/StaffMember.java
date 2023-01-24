public abstract class StaffMember {
    public String getName() {
        return name;
    }

    String name;
    String address;
    String phone;

    public StaffMember(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + " / " + "Address: " + address + " / " + "Phone: " + phone;
    }

    public abstract double calculatePayment();
}
