import java.util.Arrays;
import java.util.Comparator;

public class ShapeArray {
    private Shape[] shapes;
    private int size;

    public ShapeArray(int capacity) {
        this.shapes = new Shape[capacity];
        this.size = 0;
    }

    public void add(Shape shape) {
        if (size < shapes.length) {
            shapes[size++] = shape;
        } else {
            System.out.println("Масив заповнений!");
        }
    }

    public void displayShapes() {
        System.out.println("\n=== Набір фігур ===");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + shapes[i]);
        }
    }

    public double calcTotalArea() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += shapes[i].calcArea();
        }
        return total;
    }

    public double calcAreaByType(Class<? extends Shape> shapeType) {
        double total = 0;
        for (int i = 0; i < size; i++) {
            if (shapes[i].getClass().equals(shapeType)) {
                total += shapes[i].calcArea();
            }
        }
        return total;
    }

    public void sortByArea() {
        Arrays.sort(shapes, 0, size, new ShapeAreaComparator());
    }

    public void sortByColor() {
        Arrays.sort(shapes, 0, size, new ShapeColorComparator());
    }

    public void sort(Comparator<Shape> comparator) {
        Arrays.sort(shapes, 0, size, comparator);
    }

    public int getSize() {
        return size;
    }

    public Shape[] getShapes() {
        return Arrays.copyOf(shapes, size);
    }
}
