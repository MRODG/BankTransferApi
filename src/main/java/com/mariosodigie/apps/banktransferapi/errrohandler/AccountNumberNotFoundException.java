package com.mariosodigie.apps.banktransferapi.errrohandler;

public class AccountNumberNotFoundException extends RuntimeException  {
    public AccountNumberNotFoundException(String accountNumber) {
        super("Account number " + accountNumber + "is not registered.");
    }
}
