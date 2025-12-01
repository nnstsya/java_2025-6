import java.util.ArrayList;
import java.util.List;

public class Zoo {
    public List<Cage<?>> cages = new ArrayList<>();

    public void addCage(Cage<?> cage) {
        if (cage != null) {
            cages.add(cage);
        }
    }

    public int getCountOfAnimals() {
        int count = 0;
        for (Cage<?> cage : cages) {
            count += cage.getAnimalCount();
        }
        return count;
    }

    public int getCageCount() {
        return cages.size();
    }

    public List<Cage<?>> getCages() {
        return new ArrayList<>(cages);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Zoo with ").append(cages.size()).append(" cages and ")
          .append(getCountOfAnimals()).append(" animals:\n");
        for (Cage<?> cage : cages) {
            sb.append("  - ").append(cage.toString()).append("\n");
        }
        return sb.toString();
    }
}
