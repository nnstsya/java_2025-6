import java.io.*;
import java.util.*;

public class ShapeApp {
    private List<Shape> shapes;
    private ShapeFileHandler fileHandler;
    private Scanner scanner;

    public ShapeApp() {
        shapes = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ShapeApp app = new ShapeApp();
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
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== Shape Manager ===");
        System.out.println("1. Add Circle");
        System.out.println("2. Add Rectangle");
        System.out.println("3. Add Triangle");
        System.out.println("4. Display all shapes");
        System.out.println("5. Save shapes to file");
        System.out.println("6. Load shapes from file");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean handleMenuChoice(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 1:
                addCircle();
                break;
            case 2:
                addRectangle();
                break;
            case 3:
                addTriangle();
                break;
            case 4:
                displayShapes();
                break;
            case 5:
                saveShapes();
                break;
            case 6:
                loadShapes();
                break;
            case 7:
                System.out.println("Exiting...");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void addCircle() {
        try {
            System.out.print("Enter radius: ");
            double radius = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter color: ");
            String color = scanner.nextLine();
            shapes.add(new Circle(radius, color));
            System.out.println("Circle added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for radius.");
        }
    }

    private void addRectangle() {
        try {
            System.out.print("Enter width: ");
            double width = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter height: ");
            double height = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter color: ");
            String color = scanner.nextLine();
            shapes.add(new Rectangle(width, height, color));
            System.out.println("Rectangle added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for dimensions.");
        }
    }

    private void addTriangle() {
        try {
            System.out.print("Enter side a: ");
            double a = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter side b: ");
            double b = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter side c: ");
            double c = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter color: ");
            String color = scanner.nextLine();
            shapes.add(new Triangle(a, b, c, color));
            System.out.println("Triangle added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input for sides.");
        }
    }

    private void displayShapes() {
        if (shapes.isEmpty()) {
            System.out.println("No shapes added yet.");
            return;
        }
        System.out.println("\n=== All Shapes ===");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.print((i + 1) + ". ");
            shapes.get(i).display();
        }
    }

    private void saveShapes() throws IOException {
        if (shapes.isEmpty()) {
            System.out.println("No shapes to save.");
            return;
        }
        System.out.print("Enter file path to save: ");
        String filePath = scanner.nextLine();
        fileHandler = new ShapeFileHandler(filePath);
        fileHandler.saveShapes(shapes);
    }

    private void loadShapes() throws IOException, ClassNotFoundException {
        System.out.print("Enter file path to load: ");
        String filePath = scanner.nextLine();
        fileHandler = new ShapeFileHandler(filePath);
        if (!fileHandler.fileExists()) {
            System.out.println("File not found: " + filePath);
            return;
        }
        shapes = fileHandler.loadShapes();
        displayShapes();
    }
}
