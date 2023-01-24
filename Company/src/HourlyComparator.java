import java.util.Comparator;

public class HourlyComparator implements Comparator<HourlyWorker> {
    @Override
    public int compare(HourlyWorker o1, HourlyWorker o2) {
        if (o1.getHoursWorked() < o2.getHoursWorked()) {
            return 1;
        } else if (o1.getHoursWorked() > o2.getHoursWorked()) {
            return -1;
        } else return 0;
    }
}
