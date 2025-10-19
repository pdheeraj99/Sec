package org.example.mistakes;

/**
 * 🎯 Common Mistake: run() method ni direct ga call cheyadam.
 *
 * Ee example lo, manam thread.start() ki badulu thread.run() call cheste emavtundo chustam.
 * Idi multithreading ni achieve cheyadu.
 */

class SimpleTask implements Runnable {
    private String taskName;

    public SimpleTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        // Ee pani cheyadaniki 2 seconds padutundi anukundam.
        System.out.println("❌ Task '" + taskName + "' started by thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("✅ Task '" + taskName + "' finished by thread: " + Thread.currentThread().getName());
    }
}


public class MistakeCallingRunDirectly {
    public static void main(String[] args) {
        System.out.println("🚀 Main thread started.");

        SimpleTask task1 = new SimpleTask("File Download");
        SimpleTask task2 = new SimpleTask("Music Play");

        Thread downloaderThread = new Thread(task1);
        Thread musicPlayerThread = new Thread(task2);

        System.out.println("Calling run() directly on threads...");

        // MISTAKE IKKADA JARUGUTUNDI!
        // start() ki badulu run() call chestunnam.
        // Deeni valla kotha thread create avvadu. Antha pani 'main' thread lone jarugutundi.
        // First 'File Download' antha aipoyake, 'Music Play' start avutundi.
        downloaderThread.run();
        musicPlayerThread.run();

        // Ee line, paina rendu tasks aipoyake print avutundi.
        // Correct approach (start()) lo aithe, parallel ga print avvali.
        System.out.println("🏁 Main thread finished.");
    }
}
