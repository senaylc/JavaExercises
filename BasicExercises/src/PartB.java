import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PartB {
    public static void main(String[] args) {
        findName();
    }

    public static void findName() {
        Scanner scan = new Scanner(System.in);

        try {
            Path filePath = Paths.get("io/names.txt");
            ArrayList<String> names = new ArrayList<String>();
            ArrayList<String> names2 = new ArrayList<String>();
            for (String line : Files.readAllLines(filePath)) {
                names.add(line);
                names2.add(line.toLowerCase());
            }

            ArrayList<String> foundNames = new ArrayList<String>();
            System.out.print("Type to search in names database: ");
            String query = scan.next();
            for (int i = 0; i < names2.size(); i++) {
                if ((names2.get(i)).contains(query)) {
                    foundNames.add(names.get(i));
                    System.out.println(names.get(i));
                }
            }
            if (foundNames.size() == 0) {
                System.out.println("No results found for '" + query + "'.");
            }
            yesOrNo();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void yesOrNo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("If you want to continue type Y. Otherwise N.");
        if (scan.hasNextLine()) {
            String yn = scan.next();
            if (yn.equalsIgnoreCase("y"))
                findName();
            else if (yn.equalsIgnoreCase("n"))
                System.exit(0);
            else
                yesOrNo();

        }
    }
}

