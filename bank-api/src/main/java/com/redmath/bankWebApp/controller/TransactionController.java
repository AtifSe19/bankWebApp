package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Transaction> viewTransactionsHistory(@PathVariable String username){
        return transactionService.viewTransactionsHistory(username);
    }

    @PutMapping("/deposit/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public void depositCash(@PathVariable String username, @RequestBody Balance balance){
        transactionService.depositCash(username, balance);
    }

    @PutMapping("/withdraw/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public void withdrawCash(@PathVariable String username, @RequestBody Balance balance){
        transactionService.withdrawCash(username, balance);
    }
}