import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    final static String OUTPUT_FILE = "io/output.txt";
    final static String EXPECTED_OUTPUT_FILE = "io/expected_output.txt";

    static void executeTestCommands() {
        HTMLChecker.isValid("<p><b>Hello! </b><u>This is underlined text and this is <b>both bold and underlined.</b>");
        HTMLChecker.isValid("<p><b>Hello! </b><u><b>This is underlined text and this is <b>both bold and underlined.</u></b>");
        HTMLChecker.isValid("<p><b>Hello! </b><u>This is underlined text and this is <b>both bold and underlined.</b></u>");
        HTMLChecker.isValid("Hello! </b><u>This is underlined text and this is <b>both bold and underlined.</b></u>");
        HTMLChecker.isValid("Hello! <u>This is underlined text and this is <b>both bold and underlined.</b></u>");
        HTMLChecker.isValid("<b>Hello! </b><u>This is underlined text and this is <b>both bold and underlined.</b></u>");
        HTMLChecker.isValid("<p><b>Hello! </b><u>This is underlined text and this is <b>both bold and underlined.</b></u></p>");
    }

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
        System.out.println(testOutputFile(OUTPUT_FILE, EXPECTED_OUTPUT_FILE));
    }


    public static double testOutputFile(String outputFilepath, String expectedOutputFilepath) {
        try {
            List<String> outputLines = Files.readAllLines(Paths.get(outputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());
            List<String> expectedOutputLines = Files.readAllLines(Paths.get(expectedOutputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());

            int correct = 0;
            for (int i = 0; i < outputLines.size(); i++) {
                try {
                    if (outputLines.get(i).equals(expectedOutputLines.get(i)))
                        correct++;
                    else
                        System.out.printf("Output:\n%s\nExpected:\n%s\n", outputLines.get(i), expectedOutputLines.get(i));
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

            return correct / (double) expectedOutputLines.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
