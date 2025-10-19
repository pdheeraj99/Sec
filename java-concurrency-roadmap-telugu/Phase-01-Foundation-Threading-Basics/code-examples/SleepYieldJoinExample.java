package org.example.management;

/**
 * 🎯 Ee example lo, manam chala important thread management methods ni chuddam:
 * 1. Thread.sleep() - Kontha sep aa thread ni aapataniki.
 * 2. Thread.yield() - Current thread, CPU ni vere threads ki ivvadaniki.
 * 3. thread.join()  - Oka thread, inko thread aipoyenta varaku wait cheyadaniki.
 */
public class SleepYieldJoinExample {

    public static void main(String[] args) {
        // --- sleep() Example ---
        System.out.println("--- 😴 Thread.sleep() Example ---");
        System.out.println("Main thread is going to sleep for 2 seconds.");
        try {
            // Main thread ni 2000 milliseconds (2 seconds) aపుతున్నాం.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Sleep lo unnapudu interrupt vaste ee exception vastundi.
            e.printStackTrace();
        }
        System.out.println("Main thread woke up after 2 seconds.");
        System.out.println("-------------------------------------\n");


        // --- yield() Example ---
        // yield() behavior is not guaranteed, so idi just oka demonstration.
        // Asal idi production code lo almost vaadaru.
        System.out.println("--- 🤝 Thread.yield() Example ---");
        Runnable yieldingTask = () -> {
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() + ": Yielding my turn...");
                Thread.yield(); // "Nenu ready, kaani vere thread ki chance ista"
            }
        };
        Thread t1 = new Thread(yieldingTask, "Yielding-Thread");
        t1.start();
        // Main thread kuda yield chestundi, to give t1 a chance.
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": Yielding my turn...");
            Thread.yield();
        }
        // t1 aipoyenta varaku wait cheddam, next example clean ga start avvadaniki.
        try { t1.join(); } catch (InterruptedException e) {}
        System.out.println("-------------------------------------\n");


        // --- join() Example ---
        System.out.println("--- 🙏 thread.join() Example ---");
        System.out.println("Main thread starting a worker thread.");
        Thread workerThread = new Thread(() -> {
            System.out.println("Worker thread started. Pani cheyadaniki 3 seconds padutundi.");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Worker thread finished its work.");
        }, "Worker-Thread");

        workerThread.start();

        System.out.println("Main thread is waiting for the worker thread to finish...");
        try {
            // Main thread, workerThread aipoyenta varaku ikkade aagipotundi.
            workerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread: Worker thread has finished. Now I can proceed.");
        System.out.println("-------------------------------------");
    }
}
