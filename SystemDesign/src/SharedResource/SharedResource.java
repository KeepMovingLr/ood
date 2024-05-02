package SharedResource;

/**
 * there are 3 threads - thread1, thread2, thread3, there is a int value i,
 * thread1 will update i by adding one each time,
 * thread2 and thread3 will read value i,
 * - if i is even, thread2 will do something,
 * - if i is odd, thread3 will do something. how can I implement with java
 */
public class SharedResource {
    private int i = 0;
    private boolean isEvenTurn = false;
    private boolean isOddTurn = false;

    public synchronized void increment() {
        if (!isEvenTurn && !isOddTurn) {
            i++;
            System.out.println(Thread.currentThread().getName() + " Incremented i to: " + i);
            isEvenTurn = (i % 2 == 0); // Reset even action flag
            isOddTurn = (i % 2 == 1); // Reset even action flag
            notifyAll(); // Notify all threads that may be waiting
        } else {
            try {
                // Wait if i is not even
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void performActionIfEven() {
        if (!isEvenTurn) {
            try {
                // Wait if i is not even
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (isEvenTurn) {
            isEvenTurn = false;
            System.out.println(Thread.currentThread().getName() + " i is even and handled by Thread2: " + i);
            notifyAll(); // Notify increment thread it can proceed
        }
    }

    public synchronized void performActionIfOdd() {
        if (!isOddTurn) {
            try {
                // Wait if i is even
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (isOddTurn) {
            isOddTurn = false;
            System.out.println(Thread.currentThread().getName() + " i is odd and handled by Thread3: " + i);
            notifyAll(); // This could be used if further sequence control is needed
        }
    }

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        // Thread for incrementing i
        Thread thread1 = new Thread(() -> {
            while (true) {
                resource.increment();
            }
        });

        // Thread for checking if i is even
        Thread thread2 = new Thread(() -> {
            while (true) {
                resource.performActionIfEven();
            }
        });

        // Thread for checking if i is odd
        Thread thread3 = new Thread(() -> {
            while (true) {
                resource.performActionIfOdd();
            }
        });

        // Start all threads
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
