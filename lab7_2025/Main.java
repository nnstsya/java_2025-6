import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Perfect Numbers Finder");

        int limit = -1;
        while (limit <= 0) {
            System.out.print("Enter a positive number for the upper limit: ");
            try {
                limit = Integer.parseInt(scanner.nextLine().trim());
                if (limit <= 0) {
                    System.out.println("Error: Please enter a positive number greater than 0.\n");
                    limit = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid integer.\n");
            }
        }

        System.out.println();
        findPerfectNumbers(limit);

        scanner.close();
    }

    public static void findPerfectNumbers(int limit) {
        System.out.println("Perfect numbers from 1 to " + limit + ":");

        IntStream.rangeClosed(1, limit)
            .filter(Main::isPerfect)
            .forEach(number -> System.out.println(number + " = " + getDivisorsSum(number)));
    }

    private static boolean isPerfect(int number) {
        int divisorsSum = getSumOfDivisors(number);
        return divisorsSum == number;
    }

    private static int getSumOfDivisors(int number) {
        return IntStream.rangeClosed(1, number / 2)
            .filter(divisor -> number % divisor == 0)
            .sum();
    }

    private static String getDivisorsSum(int number) {
        return IntStream.rangeClosed(1, number / 2)
            .filter(divisor -> number % divisor == 0)
            .boxed()
            .map(Object::toString)
            .reduce((a, b) -> a + " + " + b)
            .orElse("0");
    }
}
