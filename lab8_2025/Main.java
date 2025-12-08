public class Main {
    private static final long ITERATIONS = 1_000_000_000L;
    private static long pointsInCircle = 0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <number_of_threads>");
            System.exit(1);
        }

        int numThreads = Integer.parseInt(args[0]);
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[numThreads];
        PiCalculator[] calculators = new PiCalculator[numThreads];
        long iterationsPerThread = ITERATIONS / numThreads;

        for (int i = 0; i < numThreads; i++) {
            calculators[i] = new PiCalculator(iterationsPerThread);
            threads[i] = new Thread(calculators[i]);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pointsInCircle += calculators[i].getCount();
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        double pi = 4.0 * pointsInCircle / ITERATIONS;

        System.out.printf("PI is %.5f%n", pi);
        System.out.printf("THREADS %d%n", numThreads);
        System.out.printf("ITERATIONS %,d%n", ITERATIONS);
        System.out.printf("TIME %d ms%n", elapsedTime);
    }

    static class PiCalculator implements Runnable {
        private long iterations;
        private long count = 0;

        PiCalculator(long iterations) {
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (long i = 0; i < iterations; i++) {
                double x = Math.random();
                double y = Math.random();
                if (x * x + y * y <= 1.0) {
                    count++;
                }
            }
        }

        public long getCount() {
            return count;
        }
    }
}
