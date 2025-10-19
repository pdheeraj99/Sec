package org.example.advanced;

import java.util.concurrent.ThreadFactory;

/**
 * 🎯 Production-Grade Example: Thread Naming, Exception Handling.
 *
 * Ee example lo, production-level code lo threads tho ela pani cheyalo chuddam.
 * Key aspects:
 * 1. Threads ki meaningful names ivvadam (for debugging).
 * 2. Thread lo unhandled exceptions ni ela catch cheyalo chuddam.
 */

class DataProcessor implements Runnable {
    private String data;

    public DataProcessor(String data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println("Processing data: '" + data + "' on thread " + Thread.currentThread().getName());
        try {
            // Simulate some work
            Thread.sleep(2000);

            // Simulate an error condition
            if (data.equals("invalid-data")) {
                throw new IllegalArgumentException("Hey, data sarigga ledu mawa!");
            }

            System.out.println("✅ Successfully processed data: '" + data + "'");
        } catch (InterruptedException e) {
            // Good practice: Restore the interrupted status
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted during processing.");
        }
        // Note: IllegalArgumentException ni ikkada catch cheyatledu.
        // Daanini UncaughtExceptionHandler handle chestundi.
    }
}

/**
 * Custom ThreadFactory to give meaningful names to our threads.
 * Manam Executor Framework gurinchi nerchukunapudu (Phase 6), idi chala useful.
 * 📌 Coming Up: Executor Framework
 */
class NamedThreadFactory implements ThreadFactory {
    private int count = 0;
    private String prefix = "Worker-";

    @Override
    public Thread newThread(Runnable r) {
        // Prathi kotha thread ki "Worker-0", "Worker-1" lanti peru pedutundi.
        Thread t = new Thread(r, prefix + count++);
        return t;
    }
}

public class ProductionGradeExample {
    public static void main(String[] args) {
        System.out.println("🚀 Main application started.");

        // Task 1: Good data, should complete successfully.
        DataProcessor task1 = new DataProcessor("sample-data-123");

        // Task 2: Bad data, should throw an exception.
        DataProcessor task2 = new DataProcessor("invalid-data");

        NamedThreadFactory factory = new NamedThreadFactory();
        Thread workerThread1 = factory.newThread(task1);
        Thread workerThread2 = factory.newThread(task2);

        // Define a handler for uncaught exceptions.
        // Ee handler, thread lo catch cheyani exceptions ni pattukuntundi.
        // Idi logging or cleanup ki chala important.
        Thread.UncaughtExceptionHandler handler = (thread, exception) -> {
            System.err.println("🔥 UNCAUGHT EXCEPTION in thread: " + thread.getName());
            System.err.println("   Exception: " + exception.getMessage());
            // Ikkada manam log cheyochu, or system ni alert cheyochu.
        };

        // Assign the handler to our threads.
        workerThread1.setUncaughtExceptionHandler(handler);
        workerThread2.setUncaughtExceptionHandler(handler);

        workerThread1.start();
        workerThread2.start();

        System.out.println("🏁 Main application finished starting threads.");
    }
}
