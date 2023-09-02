package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.controller.AccountHolderController;
import com.redmath.bankWebApp.controller.BalanceController;
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
    private final BalanceService balanceService;
    @Autowired
    public AccountHolderService
            (AccountHolderRepo accountHolderRepo,
             TransactionService transactionService,
             BalanceService balanceService){
        this.accountHolderRepo = accountHolderRepo;
        this.balanceService = balanceService;
    }

    public void createAccountHolder(AccountHolder user){
        accountHolderRepo.save(user);
        balanceService.createBalance(user, 0L, "CR");
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
