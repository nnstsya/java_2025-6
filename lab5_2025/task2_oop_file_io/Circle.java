public class Circle extends Shape {
    private static final long serialVersionUID = 1L;
    private double radius;

    public Circle(double radius, String color) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void display() {
        System.out.printf("Circle: radius=%.2f, color=%s, area=%.2f%n", radius, color, getArea());
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
