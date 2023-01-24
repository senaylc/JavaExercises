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
    static boolean printToOutputFile = true;

    public static void main(String[] args) {
        commandsFilepath = Paths.get(args[0]);
        outputFilepath = Paths.get(args[1]);

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        ByteArrayOutputStream baos = null;
        if (printToOutputFile) {
            baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        ArrayList<String> commandsList = readFile(commandsFilepath);
        readCommand(commandsList);

        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        if (printToOutputFile) {
            try {
                assert baos != null;
                Files.write(outputFilepath, Arrays.asList(baos.toString().trim().split("\n")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        // DON'T CHANGE THESE LINES, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
    }

    public static ArrayList<String> readFile(Path args) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scn = new Scanner(new File(String.valueOf(args)));
            list = new ArrayList<>();
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
                        if (v instanceof LargePlane) {
                            ((LargePlane) v).bookSeat(seatLabel);
                        } else ((Plane) v).bookSeat(seatLabel);
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
                        if (v instanceof TwoPlusOneBus) ((TwoPlusOneBus) v).bookSeat(seatLabel, gender);
                        else ((Bus) v).bookSeat(seatLabel, gender);
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
                            v.printSeatsTable();
                        }
                        if (v instanceof Bus) {
                            System.out.print("\n");
                            System.out.print("### START OF BUS INFO ###\n");
                            System.out.print(v + "\n");
                            v.printSeatsTable();
                        }

                    }
                }
            }
            if (c.contains("SET BASIC SEAT PRICE")) {
                String[] set = c.split((" "));
                int price = Integer.parseInt(set[4]);
                String identifier = set[6];
                for (Vehicle v : vehicles) {
                    if (v.identifier.equals(identifier)) {
                        for (Seat s : v.getSeatList()) {
                            s.setPrice(price);
                        }
                        System.out.print("Basic seat price was set to " + price + " TL for " + identifier + ".\n");
                    }
                }
            }
            if (c.contains("SET BASIC BAGGAGE LIMIT")) {
                String[] set = c.split((" "));
                int limit = Integer.parseInt(set[4]);
                String identifier = set[6];
                for (Vehicle v : vehicles) {
                    if (v instanceof Plane && v.identifier.equals(identifier)) {
                        for (Seat s : v.getSeatList()) {
                            ((PlaneSeat) s).setBaggageLimit(limit);
                        }
                        System.out.print("Basic baggage limit was set to " + limit + " kg for " + identifier + ".\n");
                    }
                }
            }
            if (c.contains("PRINT SEATSUMMARY")) {
                String[] seat = c.split((" "));
                String identifier = seat[2];
                for (Vehicle v : vehicles) {
                    if (v.identifier.equals(identifier)) {
                        v.printSeatSummaries();
                    }
                }
            }
        }
    }
}
