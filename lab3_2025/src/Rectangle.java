public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height, Color color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public String getShapeType() {
        return "Прямокутник";
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("%s (ширина: %.2f, висота: %.2f), Колір: %s, Площа: %.2f",
            getShapeType(), width, height, getShapeColor(), calcArea());
    }
}
