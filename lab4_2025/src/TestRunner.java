public class TestRunner {
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Zoo Management System - Test Suite");
        System.out.println("========================================\n");

        runTest("Test 1: Create Zoo and add cages", () -> {
            Zoo zoo = new Zoo();
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            zoo.addCage(lionCage);
            assert zoo.getCageCount() == 1 : "Failed to add cage";
        });

        runTest("Test 2: Add animals to lion cage", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");
            lionCage.addAnimal(simba);
            assert lionCage.getAnimalCount() == 1 : "Failed to add lion";
        });

        runTest("Test 3: Add multiple animals and count", () -> {
            Zoo zoo = new Zoo();
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            HoofedCage<Zebra> zebraCage = new HoofedCage<>("Zebra Enclosure", 5);

            Lion simba = new Lion("Simba");
            Zebra zeke = new Zebra("Zeke");

            lionCage.addAnimal(simba);
            zebraCage.addAnimal(zeke);

            zoo.addCage(lionCage);
            zoo.addCage(zebraCage);

            assert zoo.getCountOfAnimals() == 2 : "Failed to count animals";
        });

        runTest("Test 4: Test cage capacity limit", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 2);
            Lion simba = new Lion("Simba");
            Lion nala = new Lion("Nala");
            Lion mufasa = new Lion("Mufasa");

            lionCage.addAnimal(simba);
            lionCage.addAnimal(nala);

            assert lionCage.isFull() : "Cage should be full";
            assert lionCage.getAnimalCount() == 2 : "Should have 2 animals";

            try {
                lionCage.addAnimal(mufasa);
                assert false : "Should throw CageFullException";
            } catch (CageFullException e) {
                assert true : "Correctly threw CageFullException";
            }
        });

        runTest("Test 5: Remove animal from cage", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");

            lionCage.addAnimal(simba);
            assert lionCage.getAnimalCount() == 1 : "Failed to add animal";

            try {
                lionCage.removeAnimal(simba);
                assert lionCage.getAnimalCount() == 0 : "Failed to remove animal";
            } catch (AnimalNotInCageException e) {
                assert false : "Unexpected exception";
            }
        });

        runTest("Test 6: Exception when removing non-existent animal", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");
            Lion mufasa = new Lion("Mufasa");

            lionCage.addAnimal(simba);

            try {
                lionCage.removeAnimal(mufasa);
                assert false : "Should throw AnimalNotInCageException";
            } catch (AnimalNotInCageException e) {
                assert true : "Correctly threw AnimalNotInCageException";
            }
        });

        runTest("Test 7: Check if cage contains animal", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");
            Lion mufasa = new Lion("Mufasa");

            lionCage.addAnimal(simba);

            assert lionCage.containsAnimal(simba) : "Should contain Simba";
            assert !lionCage.containsAnimal(mufasa) : "Should not contain Mufasa";
        });

        runTest("Test 8: Get animals list", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");
            Lion nala = new Lion("Nala");

            lionCage.addAnimal(simba);
            lionCage.addAnimal(nala);

            assert lionCage.getAnimals().size() == 2 : "Should have 2 animals";
            assert lionCage.getAnimals().contains(simba) : "Should contain Simba";
            assert lionCage.getAnimals().contains(nala) : "Should contain Nala";
        });

        runTest("Test 9: Complex zoo setup with multiple cages", () -> {
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

            assert zoo.getCountOfAnimals() == 7 : "Should have 7 animals total";
            assert zoo.getCageCount() == 4 : "Should have 4 cages";
        });

        runTest("Test 10: Cage capacity display (x/y format)", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");

            lionCage.addAnimal(simba);

            String cageInfo = lionCage.toString();
            assert cageInfo.contains("1/3") : "Cage info should show '1/3'";
            assert lionCage.getMaxCapacity() == 3 : "Max capacity should be 3";
        });

        runTest("Test 11: Different cage types with different capacities", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            HoofedCage<Zebra> zebraCage = new HoofedCage<>("Zebra Enclosure", 5);
            EagleCage eagleCage = new EagleCage("Eagle Aviary", 6);

            assert lionCage.getMaxCapacity() == 3 : "Lion cage capacity should be 3";
            assert zebraCage.getMaxCapacity() == 5 : "Zebra cage capacity should be 5";
            assert eagleCage.getMaxCapacity() == 6 : "Eagle cage capacity should be 6";
        });

        runTest("Test 12: Null animal handling", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            lionCage.addAnimal(null);

            assert lionCage.getAnimalCount() == 0 : "Null animal should not be added";
        });

        runTest("Test 13: Null cage handling", () -> {
            Zoo zoo = new Zoo();
            zoo.addCage(null);

            assert zoo.getCageCount() == 0 : "Null cage should not be added";
        });

        runTest("Test 14: Type safety enforcement", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 3);
            Lion simba = new Lion("Simba");

            lionCage.addAnimal(simba);

            assert lionCage.getAnimals().size() == 1 : "Should only accept lions";
        });

        runTest("Test 15: Capacity after animal removal", () -> {
            LionCage lionCage = new LionCage("Lion Enclosure", 2);
            Lion simba = new Lion("Simba");
            Lion nala = new Lion("Nala");
            Lion mufasa = new Lion("Mufasa");

            lionCage.addAnimal(simba);
            lionCage.addAnimal(nala);
            assert lionCage.isFull() : "Cage should be full";

            try {
                lionCage.removeAnimal(simba);
                assert !lionCage.isFull() : "Cage should not be full after removal";
                lionCage.addAnimal(mufasa);
                assert lionCage.isFull() : "Cage should be full again";
            } catch (AnimalNotInCageException e) {
                assert false : "Unexpected exception";
            }
        });

        printSummary();
    }

    private static void runTest(String testName, TestCase test) {
        totalTests++;
        try {
            test.execute();
            System.out.println("✓ PASS: " + testName);
            passedTests++;
        } catch (AssertionError | Exception e) {
            System.out.println("✗ FAIL: " + testName);
            System.out.println("  Error: " + e.getMessage());
            failedTests++;
        }
    }

    private static void printSummary() {
        System.out.println("\n========================================");
        System.out.println("Test Summary");
        System.out.println("========================================");
        System.out.println("Total Tests:  " + totalTests);
        System.out.println("Passed:       " + passedTests);
        System.out.println("Failed:       " + failedTests);
        System.out.println("Success Rate: " + (totalTests > 0 ? (passedTests * 100 / totalTests) + "%" : "0%"));
        System.out.println("========================================\n");

        if (failedTests == 0) {
            System.out.println("✓ All tests passed!");
        } else {
            System.out.println("✗ Some tests failed!");
        }
    }

    @FunctionalInterface
    interface TestCase {
        void execute() throws Exception;
    }
}
