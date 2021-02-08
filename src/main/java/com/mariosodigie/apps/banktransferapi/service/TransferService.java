package com.mariosodigie.apps.banktransferapi.service;

import com.mariosodigie.apps.banktransferapi.model.Account;
import com.mariosodigie.apps.banktransferapi.model.Transaction;

import java.util.List;

public interface TransferService {

    public List<Account> allAccounts();
    public List<Transaction> allTransactions();
    public Transaction completeTransaction(Transaction transaction);
}
