package org.example.intermediate;

/**
 * 🎯 Ee example lo, okate Runnable instance ni multiple threads tho ela share cheskovacho chuddam.
 * Idi 'implements Runnable' approach yokka main advantage lo okati.
 *
 * Scenario: Oka bank account undi. Multiple people (threads) okate sari aa account nunchi
 * paisal a 'withdraw' cheyadaniki try chestunnaru.
 */
class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    // synchronized keyword gurinchi manam Phase 2 lo detailed ga matladukuntam.
    // Ippatiki, idi ee method ni okate thread okate sari access cheyagaladu ani gurthupettuko.
    // Leka pothe race condition vastundi.
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            try {
                // Simulating some processing time
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " successfully withdrew. New balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw " + amount + ", but insufficient balance. Current balance: " + balance);
        }
    }
}

/**
 * Ee Runnable task, bank account nunchi paisal a teeyadaniki try chestundi.
 */
class WithdrawalTask implements Runnable {
    // Okate BankAccount object ni multiple threads share cheskuntai.
    private final BankAccount account;

    public WithdrawalTask(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        // Prathi thread 100 rupees withdraw cheyadaniki try chestundi.
        account.withdraw(100);
    }
}

public class SharedRunnableExample {
    public static void main(String[] args) {
        // Step 1: Okate BankAccount object create cheddam. Initial balance 250.
        BankAccount sharedAccount = new BankAccount(250);

        // Step 2: Okate Runnable task object create cheddam, which holds the shared account.
        WithdrawalTask withdrawalTask = new WithdrawalTask(sharedAccount);

        // Step 3: Ee okate task ni multiple threads ki isdam.
        // Ante, mugguru persons (threads) okate account (shared object) mida pani chestunnaru.
        Thread person1 = new Thread(withdrawalTask, "Person-1");
        Thread person2 = new Thread(withdrawalTask, "Person-2");
        Thread person3 = new Thread(withdrawalTask, "Person-3");

        System.out.println("🚀 Bank account balance before transactions: 250");
        System.out.println("Mugguru okate sari 100 withdraw cheyadaniki try chestunnaru...");

        // Start all threads
        person1.start();
        person2.start();
        person3.start();
    }
}
