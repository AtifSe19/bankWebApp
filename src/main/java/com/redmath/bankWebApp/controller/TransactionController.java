package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions/{username}")
    public List<Transaction> viewTransactions(@PathVariable String username){
        return transactionService.viewTransactions(username);
    }
}
