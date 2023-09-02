package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.repo.AccountHolderRepo;
import com.redmath.bankWebApp.repo.BalanceRepo;
import com.redmath.bankWebApp.repo.TransactionRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final BalanceService balanceService;
    private final AccountHolderRepo accountHolderRepo;

    @Autowired
    public TransactionService
            (TransactionRepo transactionRepo,
             BalanceService balanceService,
             AccountHolderRepo accountHolderRepo){
        this.transactionRepo = transactionRepo;
        this.balanceService = balanceService;
        this.accountHolderRepo = accountHolderRepo;
    }
    public void createTransaction(@NotNull AccountHolder foundUser, @NotNull Balance balance, String description){

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

    public List<Transaction> viewTransactionsHistory(String username){
        return transactionRepo.findTransactionByAccountHolderUsername(username);
    }

    private static boolean isTransactionValid(Long currBal, Long amount, @NotNull String transType){

        if(transType.equals("DB"))
        {
            return amount <= currBal && amount > 0;
        }
        if(transType.equals("CR"))
        {
            return amount > 0;
        }
        return false;
    }

    private void validateTransaction(@NotNull Balance foundAccLatestBalRec, Long amount, String transType, Boolean toUpdate){
        if(!isTransactionValid(foundAccLatestBalRec.getAmount(), amount, transType)){
            throw new IllegalStateException("Insufficient funds");
        }

        if(toUpdate)
            balanceService.updateBalance(foundAccLatestBalRec, amount, transType);
        else
            balanceService.createBalance(foundAccLatestBalRec.getAccountHolder(), amount, transType);
    }


    public void handleTransaction(String username, Balance balance, String transType, String transDesc){
        AccountHolder foundAcc = accountHolderRepo.findByUsername(username).orElse(null);
        if(foundAcc == null)
            throw new IllegalStateException("User not found");

        int foundAccBalListSize = foundAcc.getBalances().size();
        Balance foundAccLatestBalRec = foundAcc.getBalances().get(foundAccBalListSize - 1);
        LocalDate latestDate = LocalDate.from(foundAccLatestBalRec.getDate());

        Boolean toUpdate = latestDate.isEqual(LocalDate.now());
        validateTransaction(foundAccLatestBalRec, balance.getAmount(), transType, toUpdate);

        createTransaction(foundAcc, balance, transDesc);
        accountHolderRepo.save(foundAcc);
    }
    public void depositCash(String username, Balance balance) {
        handleTransaction(username, balance, "CR", "Deposit");
    }

    public void withdrawCash(String username, Balance balance) {
        handleTransaction(username, balance, "DB", "Withdrawal");
    }
}
