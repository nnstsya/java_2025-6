public class MammalCage<T extends Mammal> extends Cage<T> {
    public MammalCage(String name, int maxCapacity) {
        super(name, maxCapacity);
    }
}
