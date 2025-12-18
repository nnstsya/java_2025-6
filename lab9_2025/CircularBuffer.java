import java.util.LinkedList;

public class CircularBuffer {
    private final LinkedList<String> buffer;
    private final int capacity;
    private int head = 0;
    private int tail = 0;

    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            buffer.add(null);
        }
    }

    public synchronized void put(String value) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        buffer.set(tail, value);
        tail = (tail + 1) % capacity;
        notifyAll();
    }

    public synchronized String take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        String value = buffer.get(head);
        head = (head + 1) % capacity;
        notifyAll();
        return value;
    }

    private boolean isEmpty() {
        return head == tail;
    }

    private boolean isFull() {
        return (tail + 1) % capacity == head;
    }

    public synchronized int size() {
        if (tail >= head) {
            return tail - head;
        } else {
            return capacity - head + tail;
        }
    }
}
