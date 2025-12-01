import java.util.ArrayList;
import java.util.List;

public abstract class Cage<T extends Animal> {
    protected String name;
    protected List<T> animals;
    protected int maxCapacity;

    public Cage(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.animals = new ArrayList<>();
    }

    public void addAnimal(T animal) throws CageFullException {
        if (animal == null) {
            return;
        }
        if (animals.size() >= maxCapacity) {
            throw new CageFullException("Cage " + name + " is full. Max capacity: " + maxCapacity);
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) throws AnimalNotInCageException {
        if (!animals.remove(animal)) {
            throw new AnimalNotInCageException("Animal " + animal + " is not in cage " + name);
        }
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getName() {
        return name;
    }

    public List<T> getAnimals() {
        return new ArrayList<>(animals);
    }

    public boolean containsAnimal(T animal) {
        return animals.contains(animal);
    }

    public boolean isFull() {
        return animals.size() >= maxCapacity;
    }

    @Override
    public String toString() {
        return name + " (" + animals.size() + "/" + maxCapacity + " animals)";
    }
}
