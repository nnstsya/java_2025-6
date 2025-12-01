import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Zoo Tests")
public class ZooTest {
    private Zoo zoo;
    private LionCage lionCage;
    private HoofedCage<Zebra> zebraCage;
    private HoofedCage<Giraffe> giraffeCage;
    private EagleCage eagleCage;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lionCage = new LionCage("Lion Enclosure", 3);
        zebraCage = new HoofedCage<>("Zebra Enclosure", 5);
        giraffeCage = new HoofedCage<>("Giraffe Enclosure", 4);
        eagleCage = new EagleCage("Eagle Aviary", 6);
    }

    @Test
    @DisplayName("Add cages to zoo")
    void testAddCage() {
        zoo.addCage(lionCage);
        zoo.addCage(zebraCage);

        assertEquals(2, zoo.getCageCount());
    }

    @Test
    @DisplayName("Get count of animals in empty zoo")
    void testGetCountOfAnimalsEmpty() {
        zoo.addCage(lionCage);
        zoo.addCage(zebraCage);

        assertEquals(0, zoo.getCountOfAnimals());
    }

    @Test
    @DisplayName("Add animals to cages and count total")
    void testAddAnimalsAndCount() throws CageFullException {
        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        Zebra zeke = new Zebra("Zeke");
        Zebra ziggy = new Zebra("Ziggy");

        lionCage.addAnimal(simba);
        lionCage.addAnimal(nala);
        zebraCage.addAnimal(zeke);
        zebraCage.addAnimal(ziggy);

        zoo.addCage(lionCage);
        zoo.addCage(zebraCage);

        assertEquals(4, zoo.getCountOfAnimals());
    }

    @Test
    @DisplayName("Add animals to different cage types")
    void testAddAnimalsToMultipleCageTypes() throws CageFullException {
        Lion simba = new Lion("Simba");
        Giraffe melman = new Giraffe("Melman");
        Eagle aira = new Eagle("Aira");

        lionCage.addAnimal(simba);
        giraffeCage.addAnimal(melman);
        eagleCage.addAnimal(aira);

        zoo.addCage(lionCage);
        zoo.addCage(giraffeCage);
        zoo.addCage(eagleCage);

        assertEquals(3, zoo.getCountOfAnimals());
    }

    @Test
    @DisplayName("Remove animal from cage")
    void testRemoveAnimalFromCage() throws AnimalNotInCageException, CageFullException {
        Lion simba = new Lion("Simba");
        lionCage.addAnimal(simba);

        assertEquals(1, lionCage.getAnimalCount());

        lionCage.removeAnimal(simba);

        assertEquals(0, lionCage.getAnimalCount());
    }

    @Test
    @DisplayName("Exception when removing non-existent animal")
    void testRemoveNonExistentAnimal() throws CageFullException {
        Lion simba = new Lion("Simba");
        Lion mufasa = new Lion("Mufasa");
        lionCage.addAnimal(simba);

        assertThrows(AnimalNotInCageException.class, () -> {
            lionCage.removeAnimal(mufasa);
        });
    }

    @Test
    @DisplayName("Check if cage contains animal")
    void testContainsAnimal() throws CageFullException {
        Lion simba = new Lion("Simba");
        Lion mufasa = new Lion("Mufasa");
        lionCage.addAnimal(simba);

        assertTrue(lionCage.containsAnimal(simba));
        assertFalse(lionCage.containsAnimal(mufasa));
    }

    @Test
    @DisplayName("Get cage animals list")
    void testGetAnimals() throws CageFullException {
        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        lionCage.addAnimal(simba);
        lionCage.addAnimal(nala);

        assertEquals(2, lionCage.getAnimals().size());
        assertTrue(lionCage.getAnimals().contains(simba));
        assertTrue(lionCage.getAnimals().contains(nala));
    }

    @Test
    @DisplayName("Zoo with multiple animals from different cage types")
    void testComplexZooSetup() throws AnimalNotInCageException, CageFullException {
        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        Zebra zeke = new Zebra("Zeke");
        Zebra ziggy = new Zebra("Ziggy");
        Giraffe melman = new Giraffe("Melman");
        Eagle aira = new Eagle("Aira");
        Eagle zephyr = new Eagle("Zephyr");

        lionCage.addAnimal(simba);
        lionCage.addAnimal(nala);
        zebraCage.addAnimal(zeke);
        zebraCage.addAnimal(ziggy);
        giraffeCage.addAnimal(melman);
        eagleCage.addAnimal(aira);
        eagleCage.addAnimal(zephyr);

        zoo.addCage(lionCage);
        zoo.addCage(zebraCage);
        zoo.addCage(giraffeCage);
        zoo.addCage(eagleCage);

        assertEquals(7, zoo.getCountOfAnimals());
        assertEquals(4, zoo.getCageCount());

        zebraCage.removeAnimal(zeke);
        assertEquals(6, zoo.getCountOfAnimals());
    }

    @Test
    @DisplayName("Cage name and count methods")
    void testCageProperties() throws CageFullException {
        Lion simba = new Lion("Simba");
        lionCage.addAnimal(simba);

        assertEquals("Lion Enclosure", lionCage.getName());
        assertEquals(1, lionCage.getAnimalCount());
    }

    @Test
    @DisplayName("Zoo null cage handling")
    void testAddNullCage() {
        zoo.addCage(null);

        assertEquals(0, zoo.getCageCount());
    }

    @Test
    @DisplayName("Cage null animal handling")
    void testAddNullAnimalToCage() {
        lionCage.addAnimal(null);

        assertEquals(0, lionCage.getAnimalCount());
    }

    @Test
    @DisplayName("Cage maximum capacity enforcement")
    void testCageMaxCapacity() throws CageFullException {
        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        Lion mufasa = new Lion("Mufasa");
        Lion scar = new Lion("Scar");

        lionCage.addAnimal(simba);
        lionCage.addAnimal(nala);
        lionCage.addAnimal(mufasa);

        assertEquals(3, lionCage.getAnimalCount());
        assertTrue(lionCage.isFull());

        assertThrows(CageFullException.class, () -> {
            lionCage.addAnimal(scar);
        });
    }

    @Test
    @DisplayName("Cage capacity after animal removal")
    void testCageCapacityAfterRemoval() throws AnimalNotInCageException, CageFullException {
        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        Lion mufasa = new Lion("Mufasa");
        Lion scar = new Lion("Scar");

        lionCage.addAnimal(simba);
        lionCage.addAnimal(nala);
        lionCage.addAnimal(mufasa);

        assertTrue(lionCage.isFull());

        lionCage.removeAnimal(simba);

        assertFalse(lionCage.isFull());
        assertEquals(2, lionCage.getAnimalCount());

        lionCage.addAnimal(scar);
        assertEquals(3, lionCage.getAnimalCount());
    }

    @Test
    @DisplayName("Type safety - only correct animals in cages")
    void testTypeSafety() {
        Lion simba = new Lion("Simba");

        assertTrue(lionCage.getAnimals().isEmpty());
    }

    @Test
    @DisplayName("Display cage capacity information")
    void testCageCapacityDisplay() throws CageFullException {
        Lion simba = new Lion("Simba");
        lionCage.addAnimal(simba);

        String cageInfo = lionCage.toString();
        assertTrue(cageInfo.contains("1/3"));

        assertEquals(1, lionCage.getAnimalCount());
        assertEquals(3, lionCage.getMaxCapacity());
    }

    @Test
    @DisplayName("Different cages with different capacities")
    void testMultipleCagesWithDifferentCapacities() throws CageFullException {
        Lion lion1 = new Lion("Lion1");
        Zebra zebra1 = new Zebra("Zebra1");
        Eagle eagle1 = new Eagle("Eagle1");

        lionCage.addAnimal(lion1);
        zebraCage.addAnimal(zebra1);
        eagleCage.addAnimal(eagle1);

        assertEquals(3, lionCage.getMaxCapacity());
        assertEquals(5, zebraCage.getMaxCapacity());
        assertEquals(6, eagleCage.getMaxCapacity());

        assertFalse(lionCage.isFull());
        assertFalse(zebraCage.isFull());
        assertFalse(eagleCage.isFull());
    }
}
