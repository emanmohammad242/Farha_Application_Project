package com.example.farha_application.Models;

public class wallet {
    
    
    private double balance;

    public wallet(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double Withdrawal(double amount){
        if(amount > balance || amount <0){   // her if the amount is over my balance or it is -ve (False)
            return 0;
        }else {                              // her the amount in the safe side mapy !
            balance -= amount;
            return balance;                  // this result will upbate it on data
            //TODO :upbate balance on DataBase
        }
    }
    public double Deposit(double amount){
        if(amount <= 0){
            return 0;
        }else{
            balance+=amount;
            return balance;
        }
    }

    @Override
    public String toString() {
        return "Wallet{" + "balance=" + balance + '}';
    }
}
