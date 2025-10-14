import java.util.Scanner;

public class MaxZeroInBinaryPrime {
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static String toBinary(int number) {
        if (number == 0) return "0";
        String result = "";
        while (number > 0) {
            result = (number % 2) + result;
            number /= 2;
        }
        return result;
    }

    public static int countZeros(String binaryStr) {
        int count = 0;
        for (char ch : binaryStr.toCharArray()) {
            if (ch == '0') count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть натуральне число n: ");
        int n = scanner.nextInt();

        int maxZeros = -1;
        int resultPrime = -1;
        String binaryOfResult = "";

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                String binary = toBinary(i);
                int zeros = countZeros(binary);
                if (zeros > maxZeros) {
                    maxZeros = zeros;
                    resultPrime = i;
                    binaryOfResult = binary;
                }
            }
        }

        if (resultPrime != -1) {
            System.out.println("Число з максимальною кількістю нулів у двійковій формі серед простих чисел:");
            System.out.println("Число: " + resultPrime);
            System.out.println("Двійкова форма: " + binaryOfResult);
            System.out.println("Кількість нулів: " + maxZeros);
        } else {
            System.out.println("Серед чисел до " + n + " немає простих.");
        }
    }
}
