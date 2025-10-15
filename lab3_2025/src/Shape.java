public abstract class Shape {
    private Color color;
    private String shapeColor;

    public Shape(Color color) {
        this.color = color;
        this.shapeColor = color.toString();
    }

    public abstract double calcArea();

    public abstract String getShapeType();

    public Color getColor() {
        return color;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public void setColor(Color color) {
        this.color = color;
        this.shapeColor = color.toString();
    }

    @Override
    public String toString() {
        return String.format("%s, Колір: %s, Площа: %.2f",
            getShapeType(), shapeColor, calcArea());
    }
}
