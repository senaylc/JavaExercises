public class HourlyWorker extends Employee {

    int totalHoursWorked;

    public int getHoursWorked() {
        return totalHoursWorked;
    }


    public HourlyWorker(String name, String address, String phone, String socialSecurityNumber, double payRate) {
        super(name, address, phone, socialSecurityNumber, payRate);
    }

    public void addHours(int hours) {
        totalHoursWorked += hours;
    }

    @Override
    public double calculatePayment() {
        return payRate * totalHoursWorked;
    }

    @Override
    public String toString() {
        return "Name: " + name + " / " + "Address: " + address + " / " + "Phone: " + phone + " / " + "Social Security Number: " + socialSecurityNumber + " / " + "Current hours: " + totalHoursWorked;
    }
}

