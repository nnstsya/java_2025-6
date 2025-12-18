public class BankTransferTest {
    private static final int NUM_ACCOUNTS = 100;
    private static final long INITIAL_BALANCE = 1000;
    private static final int NUM_THREADS = 5000;
    private static final int MAX_TRANSFER_AMOUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account[] accounts = new Account[NUM_ACCOUNTS];

        System.out.println("Initializing " + NUM_ACCOUNTS + " accounts with " + INITIAL_BALANCE + " each...");
        for (int i = 0; i < NUM_ACCOUNTS; i++) {
            accounts[i] = new Account(i, INITIAL_BALANCE);
        }

        long totalBefore = calculateTotalBalance(accounts);
        System.out.println("Total money in bank before transfers: " + totalBefore);

        System.out.println("\nStarting " + NUM_THREADS + " transfer threads...");
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                int fromIndex = (int) (Math.random() * NUM_ACCOUNTS);
                int toIndex = (int) (Math.random() * NUM_ACCOUNTS);
                int amount = (int) (Math.random() * MAX_TRANSFER_AMOUNT) + 1;

                bank.transfer(accounts[fromIndex], accounts[toIndex], amount);
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        long totalAfter = calculateTotalBalance(accounts);

        System.out.println("\nAll transfers completed in " + (endTime - startTime) + " ms");
        System.out.println("Total money in bank after transfers: " + totalAfter);
        System.out.println("Money preserved: " + (totalBefore == totalAfter ? "YES ✓" : "NO ✗"));

        if (totalBefore == totalAfter) {
            System.out.println("\nTest PASSED: Total money before and after are equal!");
        } else {
            System.out.println("\nTest FAILED: Total money changed!");
            System.out.println("Difference: " + (totalAfter - totalBefore));
        }
    }

    private static long calculateTotalBalance(Account[] accounts) {
        long total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
}
