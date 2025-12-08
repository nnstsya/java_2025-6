import java.io.*;
import java.util.*;

public class MaxWordsLine {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine();

            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Error: File not found!");
                return;
            }

            String lineWithMaxWords = findLineWithMaxWords(filePath);
            if (lineWithMaxWords != null) {
                System.out.println("Line with maximum words:");
                System.out.println(lineWithMaxWords);
            } else {
                System.out.println("File is empty or contains no words.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static String findLineWithMaxWords(String filePath) throws IOException {
        String maxWordsLine = null;
        int maxWords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int wordCount = countWords(line);
                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    maxWordsLine = line;
                }
            }
        }

        return maxWordsLine;
    }

    private static int countWords(String line) {
        if (line == null || line.trim().isEmpty()) {
            return 0;
        }
        return line.trim().split("\s+").length;
    }
}
