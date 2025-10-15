public class Circle extends Shape {
    private double radius;

    public Circle(double radius, Color color) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String getShapeType() {
        return "Коло";
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return String.format("%s (радіус: %.2f), Колір: %s, Площа: %.2f",
            getShapeType(), radius, getShapeColor(), calcArea());
    }
}
