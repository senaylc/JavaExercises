import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static Company company;

    public static void main(String[] args) {
        company = new Company();
        String inputPath = args[0];
        readInput(inputPath);
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
                try {
                    company.addStaffMember(e);
                } catch (InvalidCommandException ex) {
                    ex.printStackTrace();
                }
            }
            if (a.get(0).equals("Manager")) {
                Manager m = new Manager(a.get(1), a.get(2), a.get(3), a.get(4), Double.parseDouble(a.get(5)));

                try {
                    company.addStaffMember(m);
                } catch (InvalidCommandException ex) {
                    ex.printStackTrace();
                }
            }
            if (a.get(0).equals("Volunteer")) {
                Volunteer v = new Volunteer(a.get(1), a.get(2), a.get(3));
                try {
                    company.addStaffMember(v);
                } catch (InvalidCommandException ex) {
                    ex.printStackTrace();
                }
            }
            if (a.get(0).equals("Hourly")) {
                HourlyWorker hw = new HourlyWorker(a.get(1), a.get(2), a.get(3), a.get(4), Double.parseDouble(a.get(5)));
                try {
                    company.addStaffMember(hw);
                } catch (InvalidCommandException ex) {
                    ex.printStackTrace();
                }
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
