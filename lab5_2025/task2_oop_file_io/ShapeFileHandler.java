import java.io.*;
import java.util.*;

public class ShapeFileHandler {
    private String filePath;

    public ShapeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveShapes(List<Shape> shapes) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
            System.out.println("Shapes saved successfully to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving shapes: " + e.getMessage());
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Shape> loadShapes() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Shape> shapes = (List<Shape>) ois.readObject();
            System.out.println("Shapes loaded successfully from: " + filePath);
            return shapes;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading shapes: " + e.getMessage());
            throw e;
        }
    }

    public boolean fileExists() {
        return new File(filePath).exists();
    }
}
