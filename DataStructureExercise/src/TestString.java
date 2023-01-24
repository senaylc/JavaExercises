import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestString {
    final static String OUTPUT_FILE = "io/output_string_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_string_test.txt";

    public static void main(String[] args) {
        // DON'T CHANGE LINES BELOW, IT RECORDS ALL YOUR OUTPUT TO OUTPUT FILE
        PrintStream console = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        System.setOut(new PrintStream(baos));
        executeTestCommands();

        try {
            Files.write(Paths.get(OUTPUT_FILE), Arrays.asList(baos.toString().trim().split("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setOut(console);
        System.out.println(TestUtils.testOutputFile(OUTPUT_FILE, EXPECTED_OUTPUT_FILE));
    }

    static void executeTestCommands() {
        MySortedSet<String> stringSet = new MySortedSet<>();
        stringSet.add("ear");
        stringSet.add("interaction");
        stringSet.add("week");
        stringSet.add("steak");
        stringSet.add("pizza");
        stringSet.add("pizza");
        stringSet.add("football");
        stringSet.add("map");
        stringSet.add("appearance");
        stringSet.add("association");
        stringSet.add("soup");
        stringSet.add("soup");
        stringSet.add("history");
        stringSet.add("setting");
        stringSet.add("effort");
        stringSet.remove("football");
        stringSet.remove("oven");
        stringSet.remove("reputation");
        stringSet.remove("interaction");
        System.out.printf("String set contains %s => %s\n", "football", stringSet.contains("football"));
        System.out.printf("String set contains %s => %s\n", "appointment", stringSet.contains("appointment"));
        System.out.printf("String set contains %s => %s\n", "steak", stringSet.contains("steak"));
        stringSet.print();
    }
}
