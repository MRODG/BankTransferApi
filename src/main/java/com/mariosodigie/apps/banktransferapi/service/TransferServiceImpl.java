package com.mariosodigie.apps.banktransferapi.service;

import com.mariosodigie.apps.banktransferapi.errrohandler.AccountNumberNotFoundException;
import com.mariosodigie.apps.banktransferapi.errrohandler.InsufficientFundsException;
import com.mariosodigie.apps.banktransferapi.model.Account;
import com.mariosodigie.apps.banktransferapi.model.Transaction;
import com.mariosodigie.apps.banktransferapi.repository.AccountRepository;
import com.mariosodigie.apps.banktransferapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction completeTransaction(Transaction transaction) {

        String senderAccountNumber = transaction.getSourceAccountNumber();
        String recipientAccountNumber = transaction.getDestinationAccountNumber();
        BigDecimal amount = transaction.getAmount();
        Account senderAccount = validateAccount(senderAccountNumber);
        Account recipientAccount = validateAccount(recipientAccountNumber);
        if(senderAccount.getBalance().compareTo(BigDecimal.ONE) == 1
                && senderAccount.getBalance().compareTo(amount) == 1){
            senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
            accountRepository.save(senderAccount);
            recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
            accountRepository.save(recipientAccount);
            return transactionRepository.save(new Transaction(0L,senderAccountNumber, recipientAccountNumber, amount));
        }
        throw new InsufficientFundsException(senderAccountNumber);
    }

    private Account validateAccount(String accountNumber){
        Account validatedAccountNumber =  accountRepository.findByAccountNumberEquals(accountNumber);
        if(validatedAccountNumber == null)
            throw new AccountNumberNotFoundException(accountNumber);
        else
            return validatedAccountNumber;

    }

    @Override
    public List<Account> allAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public List<Transaction> allTransactions(){
        return transactionRepository.findAll();
    }

    public Account findByAccountNumber(String accountNumber){
        return accountRepository.findByAccountNumberEquals(accountNumber);
    }

    public List<Account> saveAll(List<Account> accounts){
        accountRepository.saveAll(accounts);
        return accountRepository.findAll();
    }
}
