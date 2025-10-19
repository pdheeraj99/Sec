package org.example;

class MyTask extends Thread {
    private String taskName;

    public MyTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("🔥 Task '" + taskName + "' is running in thread: " + Thread.currentThread().getName());
        for (int i = 1; i <= 3; i++) {
            System.out.println("... " + taskName + " printing " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // handle exception
            }
        }
        System.out.println("🏁 Task '" + taskName + "' finished.");
    }
}

public class StartVsRunExample {
    public static void main(String[] args) {
        System.out.println("🚀 Main thread started: " + Thread.currentThread().getName());
        System.out.println("---------------------------------------------");

        // Scenario 1: Using start() method - Correct way to start a thread
        System.out.println("SCENARIO 1: Using thread.start()");
        MyTask task1 = new MyTask("Start-Task-1");
        MyTask task2 = new MyTask("Start-Task-2");

        // start() method call cheste, JVM oka kotha thread ni create chesi, aa kotha thread lo run() method ni call chestundi.
        // Ekkada rendu kotha threads (Thread-0, Thread-1) create avutai. Main thread tho paatu parallel ga run avutai.
        task1.start();
        task2.start();

        // Konchem aagudam, ee threads complete ayyevaraku.
        try {
            task1.join(); // main thread waits for task1 to finish
            task2.join(); // main thread waits for task2 to finish
        } catch (InterruptedException e) {
            // handle exception
        }

        System.out.println("---------------------------------------------");
        System.out.println("SCENARIO 2: Using thread.run()");

        // Scenario 2: Using run() method directly - WRONG way
        MyTask task3 = new MyTask("Run-Task-3");
        MyTask task4 = new MyTask("Run-Task-4");

        // run() method ni direct ga call cheste, kotha thread em create avvadu.
        // Idi oka normal method call laaga, main thread lone execute aipotundi.
        // Ante, task3 antha complete ayyake, task4 start aithadi. (Sequential execution)
        task3.run();
        task4.run();

        System.out.println("---------------------------------------------");
        System.out.println("🏁 Main thread finished.");
    }
}
