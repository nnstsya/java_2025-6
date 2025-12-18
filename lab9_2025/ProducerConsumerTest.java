public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException {
        CircularBuffer buffer1 = new CircularBuffer(50);
        CircularBuffer buffer2 = new CircularBuffer(50);

        System.out.println("Starting Producer-Consumer test...\n");

        Thread[] producers = new Thread[5];
        for (int i = 1; i <= 5; i++) {
            final int producerId = i;
            producers[i - 1] = new Thread(() -> {
                try {
                    for (int j = 1; j <= 100; j++) {
                        String message = "Потік No " + producerId + " згенерував повідомлення " + j;
                        buffer1.put(message);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Producer-" + i);
            producers[i - 1].setDaemon(true);
            producers[i - 1].start();
        }

        Thread[] transporters = new Thread[2];
        for (int i = 1; i <= 2; i++) {
            final int transporterId = i;
            transporters[i - 1] = new Thread(() -> {
                try {
                    while (true) {
                        String message = buffer1.take();
                        String transportedMessage = "Потік No " + transporterId + " переклав повідомлення " +
                                                    message.substring(message.lastIndexOf(" ") + 1);
                        buffer2.put(transportedMessage);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Transporter-" + i);
            transporters[i - 1].setDaemon(true);
            transporters[i - 1].start();
        }

        System.out.println("Reading 100 messages from buffer2:\n");
        for (int i = 0; i < 100; i++) {
            String message = buffer2.take();
            System.out.println((i + 1) + ": " + message);
        }

        System.out.println("\nMain thread finished. Daemon threads will be terminated.");
    }
}
