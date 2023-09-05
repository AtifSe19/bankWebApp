package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("https://localhost:3000")
public class AccountHolderController {
    private final AccountHolderService accountHolderService;
    @Autowired
    public AccountHolderController(AccountHolderService accountHolderService){
        this.accountHolderService = accountHolderService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createAccountHolder(@RequestBody AccountHolder user){
        accountHolderService.createAccountHolder(user);
    }
    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Boolean> deleteAccountHolder(@PathVariable String username){
        return accountHolderService.deleteAccountHolderByUsername(username);
    }

    @PutMapping("/update/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccountHolder updateAccountHolder(@PathVariable String username, @RequestBody AccountHolder accountHolder){
        return accountHolderService.updateAccountHolder(username, accountHolder);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AccountHolder> viewAccounts(){
        return accountHolderService.getAllAccountHolders();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AccountHolder getAccountHolderById(@PathVariable Long id){
        return accountHolderService.getAccountHolderById(id);
    }

    @GetMapping("/getSpecAccHolder/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AccountHolder> getAccountHolderByUsername(@PathVariable String username){
        return accountHolderService.getAccountHolderByUsername(username);
    }

    @GetMapping("/getRoles")

    public List<String> getRoles(Authentication auth){
        return accountHolderService.getRolesByUsername(auth.getName());
    }





//    hasRole vs hasAuthority

//    if you use hasRole, then you have to mention the role as ROLE_USER (prefixing the role with ROLE) in the db, you can also mention the role as
//    ROLE_USER in the bracket of the hasRole, but writing ROLE_USER for the hasRole in the db is mandatory.

//    if you use hasAuthority, then it is not mandatory to have the role as ROLE_USER, you can simply define it as USER, but
//    the name and format of the role in the hasAuthority bracket and the db should match.

}
