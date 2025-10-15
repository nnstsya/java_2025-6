public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height, Color color) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public String getShapeType() {
        return "Трикутник";
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("%s (основа: %.2f, висота: %.2f), Колір: %s, Площа: %.2f",
            getShapeType(), base, height, getShapeColor(), calcArea());
    }
}
