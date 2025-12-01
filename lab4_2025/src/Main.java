public class Main {
    public static void main(String[] args) throws AnimalNotInCageException, CageFullException {
        System.out.println("=== Zoo Management System ===\n");

        Zoo zoo = new Zoo();

        LionCage lionCage = new LionCage("Lion Enclosure", 3);
        HoofedCage<Zebra> zebraCage = new HoofedCage<>("Zebra Enclosure", 5);
        HoofedCage<Giraffe> giraffeCage = new HoofedCage<>("Giraffe Enclosure", 4);
        EagleCage eagleCage = new EagleCage("Eagle Aviary", 6);

        Lion simba = new Lion("Simba");
        Lion nala = new Lion("Nala");
        Zebra zeke = new Zebra("Zeke");
        Zebra ziggy = new Zebra("Ziggy");
        Giraffe melman = new Giraffe("Melman");
        Eagle aira = new Eagle("Aira");
        Eagle zephyr = new Eagle("Zephyr");

        System.out.println("Adding animals to cages...");
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

        System.out.println(zoo);

        System.out.println("Removing Zeke the zebra...");
        zebraCage.removeAnimal(zeke);
        System.out.println("Total animals in zoo: " + zoo.getCountOfAnimals());
        System.out.println();

        System.out.println("=== Individual Cage Details ===");
        for (Cage<?> cage : zoo.getCages()) {
            System.out.println(cage);
        }
    }
}
