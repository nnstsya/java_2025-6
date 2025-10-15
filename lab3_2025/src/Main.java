import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ShapeArray shapeArray = new ShapeArray(10);

        Random random = new Random();
        Color[] colors = Color.values();

        System.out.println("=== Створення набору фігур ===");

        shapeArray.add(new Circle(5.0, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Circle(3.5, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Circle(7.2, colors[random.nextInt(colors.length)]));

        shapeArray.add(new Rectangle(4.0, 6.0, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Rectangle(5.5, 3.2, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Rectangle(2.8, 8.1, colors[random.nextInt(colors.length)]));

        shapeArray.add(new Triangle(6.0, 4.0, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Triangle(5.5, 6.5, colors[random.nextInt(colors.length)]));
        shapeArray.add(new Triangle(8.0, 3.5, colors[random.nextInt(colors.length)]));

        shapeArray.displayShapes();

        System.out.println("\n=== Сумарна площа всіх фігур ===");
        double totalArea = shapeArray.calcTotalArea();
        System.out.printf("Загальна площа: %.2f\n", totalArea);

        System.out.println("\n=== Сумарна площа фігур за видами ===");
        double circleArea = shapeArray.calcAreaByType(Circle.class);
        double rectangleArea = shapeArray.calcAreaByType(Rectangle.class);
        double triangleArea = shapeArray.calcAreaByType(Triangle.class);

        System.out.printf("Площа всіх кіл: %.2f\n", circleArea);
        System.out.printf("Площа всіх прямокутників: %.2f\n", rectangleArea);
        System.out.printf("Площа всіх трикутників: %.2f\n", triangleArea);

        System.out.println("\n=== Сортування за зростанням площі ===");
        shapeArray.sortByArea();
        shapeArray.displayShapes();

        System.out.println("\n=== Сортування за кольором ===");
        shapeArray.sortByColor();
        shapeArray.displayShapes();

        System.out.println("\n=== Сортування за спаданням площі (lambda) ===");
        shapeArray.sort((s1, s2) -> Double.compare(s2.calcArea(), s1.calcArea()));
        shapeArray.displayShapes();

        System.out.println("\n=== Підсумкова статистика ===");
        System.out.println("Загальна кількість фігур: " + shapeArray.getSize());
        System.out.printf("Загальна площа: %.2f\n", totalArea);
        System.out.printf("Середня площа фігури: %.2f\n", totalArea / shapeArray.getSize());
    }
}
