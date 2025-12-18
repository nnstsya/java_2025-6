public class Account {
    private long balance;
    private final int id;

    public Account(int id, long initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    public synchronized void withdraw(int amount) {
        balance -= amount;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }

    public synchronized long getBalance() {
        return balance;
    }
}
