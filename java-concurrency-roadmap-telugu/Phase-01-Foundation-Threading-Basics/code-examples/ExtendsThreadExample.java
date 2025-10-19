package org.example;

/**
 * 🎯 Ee example lo Thread class ni extend chesi oka custom thread ni create chestunnam.
 * Ee thread pani enti ante, 1 nunchi 5 varaku print cheyadam.
 */
class MyThread extends Thread {

    /**
     * run() method ni override cheyali. Ee method lo ne thread cheyalsina pani (task) ni rastam.
     * JVM ee code ni execute cheyadaniki oka kotha thread ni start chestundi.
     */
    @Override
    public void run() {
        // Ee thread em cheyali? 1 to 5 print cheyali, prathi number ki madhya 500ms gap ivvali.
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("🔥 MyThread (extends Thread): " + i);
                // Konchem gap isdam, lekapothe antha fast ga aipotundi.
                Thread.sleep(500); // 500 milliseconds aagu
            }
        } catch (InterruptedException e) {
            // Thread ni evaraina madhyalo lepithe (interrupt), ee exception vastundi.
            System.out.println("MyThread interrupted anukunta mawa!");
        }
    }
}

public class ExtendsThreadExample {
    public static void main(String[] args) {
        System.out.println("🚀 Main thread started.");

        // Step 1: Mana custom thread class ki object create cheyali.
        MyThread thread1 = new MyThread();

        // Step 2: start() method call cheyali. Idi chala important!
        // start() call cheste ne kotha thread create aithadi.
        // Direct ga thread1.run() call cheste, kotha thread create avvadu,
        // main thread lone aa code run aithadi. (📌 Next topic ide, detailed ga chuddam).
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
