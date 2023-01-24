import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Main {

    private static List<StaffMember> staffList;

    public static void main(String[] args) {
        staffList = new ArrayList<>();
        readInput(args[0]); // staff.txt
        addDataToStaff();
        printStaffAndPayments();
    }

    public static void addDataToStaff() {

        for (StaffMember s : staffList) {
            if (s instanceof HourlyWorker) {
                ((HourlyWorker) s).addHours(30);
            }
            if (s instanceof Manager) {
                ((Manager) s).awardBonus(520.5);
            }
        }
    }

    public static void printStaffAndPayments() {
        int counter = staffList.size();
        for (StaffMember s : staffList) {
            System.out.print(s.toString() + "\n");
            System.out.print(String.format(Locale.US, "%.2f", s.calculatePayment()));
            counter--;
            if (counter != 0)
                System.out.print("\n");
        }

    }

    public static void readInput(String path) {
        try {
            List<String> staff;
            staff = Files.readAllLines(Paths.get(path)); // for reading from a line
            instantiateObject(parseFile(staff));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void instantiateObject(ArrayList<ArrayList<String>> beforeStaffList) {
        for (ArrayList<String> a : beforeStaffList) {
            if (a.get(0).equals("Standard")) {
                Employee e = new Employee(a.get(1), a.get(2), a.get(3), a.get(4), Double.parseDouble(a.get(5)));
                staffList.add(e);
            }
            if (a.get(0).equals("Manager")) {
                Manager m = new Manager(a.get(1), a.get(2), a.get(3), a.get(4), Double.parseDouble(a.get(5)));
                staffList.add(m);
            }
            if (a.get(0).equals("Volunteer")) {
                Volunteer v = new Volunteer(a.get(1), a.get(2), a.get(3));
                staffList.add(v);
            }
            if (a.get(0).equals("Hourly")) {
                HourlyWorker hw = new HourlyWorker(a.get(1), a.get(2), a.get(3), a.get(4), Double.parseDouble(a.get(5)));
                staffList.add(hw);
            }
        }
    }

    public static ArrayList<ArrayList<String>> parseFile(List<String> tempList) {
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        tempList.remove(0);
        while (tempList.size() != 0) {
            ArrayList<String> list = new ArrayList<>();
            String[] array2 = (tempList.get(0)).split("\t");
            Collections.addAll(list, array2);
            array.add(list);
            tempList.remove(0);
        }
        return array;
    }
}
