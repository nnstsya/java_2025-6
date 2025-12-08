import java.io.*;
import java.util.*;

public class EncryptionApp {
    private Scanner scanner;

    public EncryptionApp() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        EncryptionApp app = new EncryptionApp();
        app.run();
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
        System.out.println("\n=== Encryption Tool ===");
        System.out.println("1. Encrypt file (byte streams)");
        System.out.println("2. Decrypt file (byte streams)");
        System.out.println("3. Encrypt file (character streams)");
        System.out.println("4. Decrypt file (character streams)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean handleMenuChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                encryptFileBytes();
                break;
            case 2:
                decryptFileBytes();
                break;
            case 3:
                encryptFileChars();
                break;
            case 4:
                decryptFileChars();
                break;
            case 5:
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void encryptFileBytes() throws IOException {
        try {
            System.out.print("Enter input file path: ");
            String inputPath = scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputPath = scanner.nextLine();
            System.out.print("Enter key character (for encryption): ");
            char keyChar = scanner.nextLine().charAt(0);

            encryptWithByteStreams(inputPath, outputPath, keyChar);
            System.out.println("File encrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
        }
    }

    private void decryptFileBytes() throws IOException {
        try {
            System.out.print("Enter encrypted file path: ");
            String inputPath = scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputPath = scanner.nextLine();
            System.out.print("Enter key character (for decryption): ");
            char keyChar = scanner.nextLine().charAt(0);

            decryptWithByteStreams(inputPath, outputPath, keyChar);
            System.out.println("File decrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error during decryption: " + e.getMessage());
        }
    }

    private void encryptFileChars() throws IOException {
        try {
            System.out.print("Enter input file path: ");
            String inputPath = scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputPath = scanner.nextLine();
            System.out.print("Enter key character (for encryption): ");
            char keyChar = scanner.nextLine().charAt(0);

            encryptWithCharStreams(inputPath, outputPath, keyChar);
            System.out.println("File encrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error during encryption: " + e.getMessage());
        }
    }

    private void decryptFileChars() throws IOException {
        try {
            System.out.print("Enter encrypted file path: ");
            String inputPath = scanner.nextLine();
            System.out.print("Enter output file path: ");
            String outputPath = scanner.nextLine();
            System.out.print("Enter key character (for decryption): ");
            char keyChar = scanner.nextLine().charAt(0);

            decryptWithCharStreams(inputPath, outputPath, keyChar);
            System.out.println("File decrypted successfully!");
        } catch (Exception e) {
            System.err.println("Error during decryption: " + e.getMessage());
        }
    }

    public static void encryptWithByteStreams(String inputPath, String outputPath, char keyChar)
            throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath);
             CipherOutputStream cos = new CipherOutputStream(fos, keyChar)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void decryptWithByteStreams(String inputPath, String outputPath, char keyChar)
            throws IOException {
        try (FileInputStream fis = new FileInputStream(inputPath);
             CipherInputStream cis = new CipherInputStream(fis, keyChar);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }

    public static void encryptWithCharStreams(String inputPath, String outputPath, char keyChar)
            throws IOException {
        try (FileReader fr = new FileReader(inputPath);
             FileWriter fw = new FileWriter(outputPath);
             CipherWriterFilter cwf = new CipherWriterFilter(fw, keyChar)) {

            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = fr.read(buffer)) != -1) {
                cwf.write(buffer, 0, charsRead);
            }
            cwf.flush();
        }
    }

    public static void decryptWithCharStreams(String inputPath, String outputPath, char keyChar)
            throws IOException {
        try (FileReader fr = new FileReader(inputPath);
             CipherReaderFilter crf = new CipherReaderFilter(fr, keyChar);
             FileWriter fw = new FileWriter(outputPath)) {

            char[] buffer = new char[1024];
            int charsRead;
            while ((charsRead = crf.read(buffer)) != -1) {
                fw.write(buffer, 0, charsRead);
            }
            fw.flush();
        }
    }
}
