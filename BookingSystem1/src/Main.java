import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Path commandsFilepath;
    static Path outputFilepath;
    static List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        commandsFilepath = Paths.get(args[0]);
        outputFilepath = Paths.get(args[1]);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        ArrayList<String> commandsList = readFile(commandsFilepath);
        readCommand(commandsList);


        try {
            Files.write(outputFilepath, Arrays.asList(baos.toString().trim().split("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> readFile(Path args) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner scn = new Scanner(new File(String.valueOf(args)));
            list = new ArrayList<String>();
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                list.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void readCommand(ArrayList<String> commandList) {
        for (String c : commandList) {
            if (c.contains("ADD VEHICLE PLANE")) {
                vehicles.add(Command.addVehiclePlane(c));
            }
            if (c.contains("ADD VEHICLE BUS")) {
                vehicles.add(Command.addVehicleBus(c));
            }
            if (c.contains("BOOK PLANESEAT")) {
                String[] book = c.split(" ");
                String planeIdentifier = book[4];
                String seatLabel = book[2];
                for (Vehicle v : vehicles) {
                    if (v.identifier.equals(planeIdentifier)) {
                        ((Plane)v).bookSeat(seatLabel);
                    }
                }
            }
            if (c.contains("BOOK BUSSEAT")) {
                String[] book = c.split((" "));
                String seatLabel = book[2];
                String g = book[3];
                char gender = g.charAt(0);
                String busIdentifier = book[5];
                for (Vehicle v : vehicles) {
                    if (v.identifier.equals(busIdentifier)) {
                        ((Bus)v).bookSeat(seatLabel,gender);
                    }
                }
            }
            if (c.contains("PRINT SEATTABLE")) {
                String[] print = c.split((" "));
                String identifier = print[2];

                for (Vehicle v : vehicles) {
                    if (v.identifier.equals(identifier)) {
                        if (v instanceof Plane) {
                            System.out.print("\n");
                            System.out.print("### START OF PLANE INFO ###\n");
                            System.out.print(v + "\n");
                        }
                        if (v instanceof Bus) {
                            System.out.print("\n");
                            System.out.print("### START OF BUS INFO ###\n");
                            System.out.print(v + "\n");
                        }
                        v.printSeatsTable();
                    }
                }
            }
        }
    }
}
