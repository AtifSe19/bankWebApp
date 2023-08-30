package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{username}")
    public List<Transaction> viewTransactionsHistory(@PathVariable String username){
        return transactionService.viewTransactionsHistory(username);
    }

    @PutMapping("/deposit/{username}")
    public void depositCash(@PathVariable String username){
        transactionService.depositCash(username);
    }

    @PutMapping("/withdraw/{username}")
    public void withdrawCash(@PathVariable String username){
        transactionService.withdrawCash(username);
    }
}
