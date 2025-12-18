public class Bank {
    public void transfer(Account from, Account to, int amount) {
        if (from.getId() == to.getId()) {
            return;
        }

        Account first, second;
        if (from.getId() < to.getId()) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }
}
