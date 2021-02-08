package com.mariosodigie.apps.banktransferapi.errrohandler;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String accountNumber) {
        super("Insufficient funds in account " + accountNumber);
    }
}
