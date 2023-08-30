package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.controller.AccountHolderController;
import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.model.Transaction;
import com.redmath.bankWebApp.repo.AccountHolderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountHolderService {

    private final AccountHolderRepo accountHolderRepo;
    private final TransactionService transactionService;
    @Autowired
    public AccountHolderService(AccountHolderRepo accountHolderRepo, TransactionService transactionService){
        this.transactionService = transactionService;
        this.accountHolderRepo = accountHolderRepo;
    }

    public void createAccountHolder(AccountHolder user){
        Balance bal = new Balance();
        bal.setAmount(0L);
        bal.setAccountHolder(user);
        bal.setDate(LocalDateTime.now());
        bal.setDb_cr_indicator("CR");
        user.setBalances(List.of(bal));

        accountHolderRepo.save(user);
    }

    public void depositCash(String username, Balance balance) {
        AccountHolder foundUser = accountHolderRepo.findByUsername(username).orElse(null);
        if(foundUser == null){
            throw new IllegalStateException("User not found");
        }
        else {

            int foundUserBalListSize = foundUser.getBalances().size();
            Balance latestBalTransaction = foundUser.getBalances().get(foundUserBalListSize - 1);
            LocalDate latestDate = LocalDate.from(latestBalTransaction.getDate());

            if(latestDate.isEqual(LocalDate.now())){
                latestBalTransaction.setAmount(latestBalTransaction.getAmount() + balance.getAmount());
                latestBalTransaction.setDb_cr_indicator("CR");
            }

            else {
                balance.setAmount(balance.getAmount());
                foundUser.getBalances().add(balance);
                balance.setDb_cr_indicator("CR");
                balance.setAccountHolder(foundUser);
            }
            transactionService.createTransaction(foundUser, balance, "Deposit");
            accountHolderRepo.save(foundUser);
        }
    }

    public void withdrawCash(String username, Balance balance) {
        AccountHolder foundUser = accountHolderRepo.findByUsername(username).orElse(null);
        if(foundUser == null){
            throw new IllegalStateException("User not found");
        }
        else {
            int foundUserBalListSize = foundUser.getBalances().size();
            Balance latestBalTransaction = foundUser.getBalances().get(foundUserBalListSize - 1);
            LocalDate latestDate = LocalDate.from(latestBalTransaction.getDate());

            if(latestDate.isEqual(LocalDate.now())){

                if(latestBalTransaction.getAmount() < balance.getAmount()){
                    throw new IllegalStateException("Insufficient funds");
                }
                else {
                    latestBalTransaction.setAmount(latestBalTransaction.getAmount() - balance.getAmount());
                    latestBalTransaction.setDb_cr_indicator("DB");
                }
            }

            else {
                balance.setAmount(balance.getAmount());
                foundUser.getBalances().add(balance);
                balance.setDb_cr_indicator("DB");
                balance.setAccountHolder(foundUser);
            }
            transactionService.createTransaction(foundUser, balance, "Withdrawal");
            accountHolderRepo.save(foundUser);
        }
    }

    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepo.findAll();
    }


    public AccountHolder updateAccountHolder(Long id, AccountHolder accountHolder) {
        AccountHolder foundUser = accountHolderRepo.findById(id).orElse(null);

        foundUser.setUsername(foundUser.getUsername());
        foundUser.setEmail(foundUser.getEmail());
        foundUser.setPassword(foundUser.getPassword());
        foundUser.setRoles(foundUser.getRoles());
        foundUser.setAddress(foundUser.getAddress());

        return accountHolderRepo.save(foundUser);
    }

    public void deleteAccountHolder(Long id) {
        accountHolderRepo.deleteById(id);
    }

    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepo.findById(id).orElse(null);
    }
}
