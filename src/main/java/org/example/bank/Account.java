package org.example.bank;


/**
 * Represents a bank account with thread-safe operations.
 */
public class Account {

    // Fields
    private final int id;
    private double balance;

    public Account(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public int getId() {
        return id;
    }

    /**
     * Why use synchronized here? 'Best Practice'
     * The reading thread sees the most up-to-date value of balance.
     * Prevents weird race conditions in case other methods that modify balance are also synchronized.
     * */
    public synchronized double getBalance(){
        return balance;
    }

    /**
     * 'Best Practice'
     * {@code synchronized} makes it thread-safe, so two threads don’t deposit at the same time and mess up the balance.
     * */
    public synchronized void deposit(double amount){
        balance+=amount;
    }

    /**
     * Takes money out of the account if there’s enough money.
     * {@code synchronized} prevents two threads from withdrawing at the same time and causing an overdraft.
     * */
    public synchronized boolean withdraw(double amount){
        if(amount > balance){
            System.out.println("You don't have "+amount+"in your balance");
            System.out.println("Your balance is "+getBalance());
            return false;
        }
        balance-=amount;
        return true;
    }



}
