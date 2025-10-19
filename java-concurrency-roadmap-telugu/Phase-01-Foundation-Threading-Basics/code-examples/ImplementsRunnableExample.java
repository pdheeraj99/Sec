package org.example;

/**
 * 🎯 Ee example lo Runnable interface ni implement chesi oka task create chestunnam.
 * Idi thread ni create cheyadaniki recommended way.
 */
class MyRunnable implements Runnable {

    /**
     * run() method lo manam cheyalsina task ni define chestam.
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("🔥 MyRunnable (implements Runnable): " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("MyRunnable interrupted anukunta mawa!");
        }
    }
}

public class ImplementsRunnableExample {
    public static void main(String[] args) {
        System.out.println("🚀 Main thread started.");

        // Step 1: Mana Runnable task ki object create cheyali.
        MyRunnable myRunnable = new MyRunnable();

        // Step 2: Thread class object create chesi, daani constructor ki mana runnable task ni pass cheyali.
        // Manam task ni (MyRunnable) thread behavior (Thread class) nunchi separate chestunnam.
        Thread thread1 = new Thread(myRunnable);

        // Step 3: start() method call cheyali to create a new thread.
        thread1.start();

        // Main thread pani adi cheskuntundi.
        for (int i = 1; i <= 3; i++) {
            System.out.println("✅ Main thread pani chestundi... " + i);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                // handle exception
            }
        }

        System.out.println("🏁 Main thread finished.");
    }
}
