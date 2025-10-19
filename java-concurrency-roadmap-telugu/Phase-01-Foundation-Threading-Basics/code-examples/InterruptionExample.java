package org.example.management;

/**
 * 🎯 Ee example lo, thread interruption ni ela handle cheyalo nerchukuntam.
 * Idi oka thread ni gracefully shutdown cheyadaniki correct way.
 * `thread.stop()` anedi deprecated and dangerous, so vaadakudadu.
 */
class LongRunningTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Worker thread started. I will work until interrupted.");

        // Loop antha varaku run avvali antha varaku interrupt flag set avvadu.
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Worker is doing some work...");
            try {
                // Kontha sep sleep cheddam. sleep() method interruption ki respond avutundi.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // sleep() lo unnapudu interrupt vaste, ee exception vastundi.
                // InterruptedException vachinapudu, 'interrupted' status clear aipotundi.
                // So, manam daanini malli set cheyali, so that the loop condition works correctly.
                System.out.println("Worker was sleeping and got interrupted. Restoring interrupt status.");
                Thread.currentThread().interrupt(); // Re-interrupt the thread
            }
        }

        System.out.println("Worker thread detected interruption and is shutting down gracefully.");
    }
}

public class InterruptionExample {
    public static void main(String[] args) {
        System.out.println("Main thread started.");

        LongRunningTask task = new LongRunningTask();
        Thread workerThread = new Thread(task, "Worker-Thread");
        workerThread.start();

        System.out.println("Main thread will let the worker run for 3 seconds.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Ippudu worker thread ni interrupt cheddam.
        // Idi just oka request, oka flag ni set chestundi.
        System.out.println("Main thread is sending an interrupt request to the worker thread.");
        workerThread.interrupt();

        // Worker thread aipoyenta varaku wait cheddam.
        try {
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished.");
    }
}
