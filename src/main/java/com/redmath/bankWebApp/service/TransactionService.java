package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.repo.AccountHolderRepo;
import com.redmath.bankWebApp.repo.BalanceRepo;
import com.redmath.bankWebApp.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final BalanceRepo balanceRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo, BalanceRepo balanceRepo){
        this.transactionRepo = transactionRepo;
        this.balanceRepo = balanceRepo;
    }
    public void createTransaction(AccountHolder foundUser, Balance balance, String description){

        Transaction transaction = new Transaction();
        transaction.setDate(LocalDateTime.now());
        transaction.setDescription(description);
        String db_or_cr = description.equals("Deposit") ? "CR" : "DB";
        transaction.setDb_cr_indicator(db_or_cr);
        transaction.setAmount(balance.getAmount());
        foundUser.getTransactions().add(transaction);
        transaction.setAccountHolder(foundUser);
        transactionRepo.save(transaction);

    }

    public List<Transaction> viewTransactions(String username){
        return transactionRepo.findTransactionByAccountHolderUsername(username);
    }

}
