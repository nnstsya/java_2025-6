import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class TagFrequencyCounter {
    private Scanner scanner;

    public TagFrequencyCounter() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        TagFrequencyCounter counter = new TagFrequencyCounter();
        counter.run();
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                running = handleMenuChoice(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Tag Frequency Counter ===");
        System.out.println("1. Count tags from URL (sorted alphabetically)");
        System.out.println("2. Count tags from URL (sorted by frequency)");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean handleMenuChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                countTagsAlphabetically();
                break;
            case 2:
                countTagsByFrequency();
                break;
            case 3:
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void countTagsAlphabetically() throws IOException {
        try {
            System.out.print("Enter URL: ");
            String urlString = scanner.nextLine().trim();

            Map<String, Integer> tagFrequency = getTagFrequency(urlString);
            displaySortedAlphabetically(tagFrequency);
        } catch (IOException e) {
            System.err.println("Error fetching URL: " + e.getMessage());
        }
    }

    private void countTagsByFrequency() throws IOException {
        try {
            System.out.print("Enter URL: ");
            String urlString = scanner.nextLine().trim();

            Map<String, Integer> tagFrequency = getTagFrequency(urlString);
            displaySortedByFrequency(tagFrequency);
        } catch (IOException e) {
            System.err.println("Error fetching URL: " + e.getMessage());
        }
    }

    private Map<String, Integer> getTagFrequency(String urlString) throws IOException {
        URL url = new URL(urlString);
        Map<String, Integer> tagFrequency = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            StringBuilder htmlContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }

            extractTagFrequency(htmlContent.toString(), tagFrequency);
        }

        return tagFrequency;
    }

    private void extractTagFrequency(String htmlContent, Map<String, Integer> tagFrequency) {
        Pattern pattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(htmlContent);

        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
        }
    }

    private void displaySortedAlphabetically(Map<String, Integer> tagFrequency) {
        if (tagFrequency.isEmpty()) {
            System.out.println("No tags found.");
            return;
        }

        System.out.println("\n=== Tags sorted alphabetically ===");
        tagFrequency.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .forEach(entry -> System.out.printf("%-20s : %d%n", entry.getKey(), entry.getValue()));
    }

    private void displaySortedByFrequency(Map<String, Integer> tagFrequency) {
        if (tagFrequency.isEmpty()) {
            System.out.println("No tags found.");
            return;
        }

        System.out.println("\n=== Tags sorted by frequency ===");
        tagFrequency.entrySet().stream()
                .sorted((e1, e2) -> {
                    int freqCompare = Integer.compare(e2.getValue(), e1.getValue());
                    if (freqCompare != 0) {
                        return freqCompare;
                    }
                    return e1.getKey().compareTo(e2.getKey());
                })
                .forEach(entry -> System.out.printf("%-20s : %d%n", entry.getKey(), entry.getValue()));
    }
}
