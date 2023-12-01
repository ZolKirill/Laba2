public class CustomSemaphore {
    private int permits;
    private final Object lock = new Object();
    private long timeout; // Время ожидания

    public CustomSemaphore(int permits, long timeout) {
        this.permits = permits;
        this.timeout = timeout;
    }

    public void acquire() throws InterruptedException {
        synchronized (lock) {
            if (timeout > 0) {
                long startTime = System.currentTimeMillis();
                long elapsedTime;
                while (permits <= 0) {
                    lock.wait(timeout);
                    elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime >= timeout) {
                        break;
                    }
                }
            } else {
                while (permits <= 0) {
                    lock.wait();
                }
            }
            permits--;
        }
    }

    public void release() {
        synchronized (lock) {
            permits++;
            lock.notify();
        }
    }
}

