import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class TestNumbers {
    final static String OUTPUT_FILE = "io/output_numbers_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_numbers_test.txt";

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
        MySortedSet<Integer> integerSet = new MySortedSet<>();
        integerSet.add(17);
        integerSet.add(15);
        integerSet.add(102);
        integerSet.add(11);
        integerSet.add(14);
        integerSet.add(-50);
        integerSet.add(135);
        integerSet.add(135);
        integerSet.add(105);
        integerSet.add(51);
        integerSet.add(166);
        integerSet.add(166);
        integerSet.add(193);
        integerSet.add(94);
        integerSet.add(100);
        integerSet.add(19);
        integerSet.add(12);
        integerSet.add(-57);
        integerSet.add(76);
        integerSet.add(-3);
        integerSet.add(-3);
        integerSet.add(-80);
        integerSet.add(-4);
        integerSet.add(-18);
        integerSet.add(-72);
        integerSet.remove(-72);
        integerSet.remove(30);
        integerSet.remove(66);
        System.out.printf("Integer set contains %d => %s\n", 76, integerSet.contains(76));
        System.out.printf("Integer set contains %d => %s\n", 66, integerSet.contains(66));
        integerSet.print();
    }


}
