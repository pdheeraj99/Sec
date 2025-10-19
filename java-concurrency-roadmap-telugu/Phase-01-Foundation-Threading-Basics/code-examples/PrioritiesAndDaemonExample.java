package org.example.management;

/**
 * 🎯 Ee example lo, manam inko rendu important concepts chuddam:
 * 1. Thread Priorities - Thread scheduler ki ఏ thread ki ekkuva importance ivvalo cheppadaniki.
 * 2. Daemon Threads - Background lo pani chese threads, main application aagipothe ive kuda aagipotai.
 */
public class PrioritiesAndDaemonExample {
    public static void main(String[] args) {

        // --- Priorities Example ---
        System.out.println("--- 🚀 Thread Priorities Example ---");
        Runnable priorityTask = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is running.");
            }
        };

        Thread lowPriorityThread = new Thread(priorityTask, "LowPriorityThread");
        Thread highPriorityThread = new Thread(priorityTask, "HighPriorityThread");

        // Set priorities
        // Priorities are just a hint to the scheduler. Guarantee em ledu.
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY); // Priority 1
        highPriorityThread.setPriority(Thread.MAX_PRIORITY); // Priority 10

        System.out.println("Starting threads with different priorities...");
        highPriorityThread.start();
        lowPriorityThread.start();

        // Wait for them to finish before starting the next example
        try {
            lowPriorityThread.join();
            highPriorityThread.join();
        } catch (InterruptedException e) {}
        System.out.println("-------------------------------------\n");


        // --- Daemon Thread Example ---
        System.out.println("--- 👻 Daemon Thread Example ---");
        Thread daemonThread = new Thread(() -> {
            // Ee thread antha sep run avtane untadi.
            while (true) {
                System.out.println("Daemon thread is running in the background...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // This might happen if main thread exits.
                    break;
                }
            }
        }, "MyDaemonThread");

        // Ee thread ni daemon ga set chestunnam. Idi main thread aagipogane aagipotundi.
        daemonThread.setDaemon(true);
        daemonThread.start();

        System.out.println("Main thread is running for 3 seconds...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}

        System.out.println("Main thread is finishing. JVM will now exit, stopping the daemon thread.");
        // Main thread aipoyindi, so JVM antha non-daemon threads aipoyayi anukuni exit aipotundi.
        // Daemon thread antha abruptly stop aipotundi.
    }
}
