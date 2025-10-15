public enum Color {
    RED("Червоний"),
    BLUE("Синій"),
    GREEN("Зелений"),
    YELLOW("Жовтий"),
    BLACK("Чорний"),
    WHITE("Білий");

    private final String ukrainianName;

    Color(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    @Override
    public String toString() {
        return ukrainianName;
    }
}
