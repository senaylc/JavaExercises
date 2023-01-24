public class Manager extends Employee {
    double bonus;

    public Manager(String name, String address, String phone, String socialSecurityNumber, double payRate) {
        super(name, address, phone, socialSecurityNumber, payRate);
    }

    public void awardBonus(double bonus) {
        this.bonus += bonus;
    }

    @Override
    public double calculatePayment() {
        return bonus + super.calculatePayment();
    }

}
