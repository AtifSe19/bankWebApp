package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountHolderController {
    private final AccountHolderService accountHolderService;
    @Autowired
    public AccountHolderController(AccountHolderService accountHolderService){
        this.accountHolderService = accountHolderService;
    }

    @PostMapping
    public void createAccountHolder(@RequestBody AccountHolder user){
        accountHolderService.createAccountHolder(user);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAccountHolder(@PathVariable Long id){
        accountHolderService.deleteAccountHolder(id);
    }

    @PutMapping("/update/{id}")
    public AccountHolder updateAccountHolder(@PathVariable Long id, @RequestBody AccountHolder accountHolder){
        return accountHolderService.updateAccountHolder(id, accountHolder);
    }
    @GetMapping
    public List<AccountHolder> viewAccounts(){
        return accountHolderService.getAllAccountHolders();
    }

    @GetMapping("/{id}")
    public AccountHolder getAccountHolderById(@PathVariable Long id){
        return accountHolderService.getAccountHolderById(id);
    }

}
