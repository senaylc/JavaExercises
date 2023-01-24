import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(testOutputFile(args[0], args[1]));
    }

    private static double testOutputFile(String outputFilepath, String expectedOutputFilepath) {
        try {
            List<String> outputLines = Files.readAllLines(Paths.get(outputFilepath));
            List<String> expectedOutputLines = Files.readAllLines(Paths.get(expectedOutputFilepath));

            int correct = 0;
            for(int i = 0; i < outputLines.size(); i++) {
                try {
                    if(outputLines.get(i).equalsIgnoreCase(expectedOutputLines.get(i)))
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
