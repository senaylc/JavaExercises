import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        // args[0]: staff.txt
        // args[1]: output.txt
        // args[2]: expected_output.txt

        PrintStream console = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Main.main(args);

        try {
            Files.write(Paths.get(args[1]), Arrays.asList(baos.toString().trim().split("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.setOut(console);
        System.out.println(testOutputFile(args[1], args[2]));
    }

    private static double testOutputFile(String outputFilepath, String expectedOutputFilepath) {
        try {
            List<String> outputLines = Files.readAllLines(Paths.get(outputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());
            List<String> expectedOutputLines = Files.readAllLines(Paths.get(expectedOutputFilepath)).stream().map(String::trim).filter(line -> !line.isEmpty()).collect(Collectors.toList());

            int correct = 0;
            for(int i = 0; i < outputLines.size(); i++) {
                try {
                    if(outputLines.get(i).equals(expectedOutputLines.get(i)))
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
