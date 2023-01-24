import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestPerson {
    final static String OUTPUT_FILE = "io/output_person_test.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output_person_test.txt";

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
        Person aylin = new Person("Aylin", 4);
        Person mehtap = new Person("Mehtap", 41);
        Person metin = new Person("Metin", 8);
        Person sahin = new Person("Sahin", 30);
        Person suna = new Person("Suna", 26);
        Person mustafa = new Person("Mustafa", 10);
        Person yagmur = new Person("Yagmur", 47);
        Person yasemin = new Person("Yasemin", 9);

        MySortedSet<Person> personSet = new MySortedSet<>();
        personSet.add(aylin);
        personSet.add(metin);
        personSet.add(metin);
        personSet.add(sahin);
        personSet.add(mustafa);
        personSet.add(mustafa);
        personSet.add(yagmur);
        personSet.add(yasemin);
        personSet.remove(mehtap);
        System.out.printf("Person set contains %s => %s\n", yagmur, personSet.contains(yagmur));
        System.out.printf("Person set contains %s => %s\n", mehtap, personSet.contains(mehtap));
        System.out.printf("Person set contains %s => %s\n", suna, personSet.contains(suna));
        personSet.print();
    }


}
