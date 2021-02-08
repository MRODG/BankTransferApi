package com.mariosodigie.apps.banktransferapi.controllers;

import com.mariosodigie.apps.banktransferapi.model.Account;
import com.mariosodigie.apps.banktransferapi.model.Transaction;
import com.mariosodigie.apps.banktransferapi.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/bank-transfer")
public class TransferController {

    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/accounts")
    public List<Account> accounts(){
        return (transferService.allAccounts());
    }

    @GetMapping("/transactions")
    public List<Transaction> transactions(){
        return (transferService.allTransactions());
    }

    @PostMapping("/completeTransfer")
    public ResponseEntity<Transaction> completeTransfer(@RequestBody Transaction transaction){
        return ResponseEntity.ok().body(transferService.completeTransaction(transaction));
    }
}
