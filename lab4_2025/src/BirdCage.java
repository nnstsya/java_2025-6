public class BirdCage<T extends Bird> extends Cage<T> {
    public BirdCage(String name, int maxCapacity) {
        super(name, maxCapacity);
    }
}
