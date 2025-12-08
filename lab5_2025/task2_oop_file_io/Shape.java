import java.io.Serializable;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();

    public abstract void display();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
