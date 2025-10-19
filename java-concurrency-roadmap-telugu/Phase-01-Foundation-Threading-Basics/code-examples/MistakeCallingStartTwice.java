package org.example.mistakes;

/**
 * 🎯 Common Mistake: Calling start() more than once on the same thread.
 *
 * Ee example lo, manam okate thread object mida start() method ni multiple times
 * call cheste emavutundo chustam.
 * Idi IllegalThreadStateException ki lead chestundi.
 */
public class MistakeCallingStartTwice {
    public static void main(String[] args) {
        Thread myThread = new Thread(() -> {
            System.out.println("My thread is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Calling start() for the first time...");
        myThread.start();

        // Konchem aagi, thread state chuddam.
        try {
            Thread.sleep(500);
            System.out.println("Thread state after first start(): " + myThread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Calling start() for the second time... Idi exception isthundi!");
            myThread.start(); // ❌ MISTAKE!
        } catch (IllegalThreadStateException e) {
            System.err.println("🔥 Correctly caught the expected exception: " + e.getClass().getName());
            System.err.println("   Message: " + e.getMessage());
        }
    }
}
