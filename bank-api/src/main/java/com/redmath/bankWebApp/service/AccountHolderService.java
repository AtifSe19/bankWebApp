package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.repo.AccountHolderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountHolderService implements UserDetailsService {
    private final AccountHolderRepo accountHolderRepo;

    private final BalanceService balanceService;
    @Autowired
    public AccountHolderService(AccountHolderRepo accountHolderRepo, BalanceService balanceService){
        this.accountHolderRepo = accountHolderRepo;
        this.balanceService = balanceService;
    }

    public AccountHolder findByUsername(String username){
        return accountHolderRepo.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountHolder accHolder = findByUsername(username);
        if(accHolder == null){
            System.out.println(username);
            throw new UsernameNotFoundException(username + "not found");
        }
        return new User(accHolder.getUsername(), accHolder.getPassword(), true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(accHolder.getRoles()));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public void createAccountHolder(AccountHolder user){
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        accountHolderRepo.save(user);
        if(user.getRoles().equalsIgnoreCase("USER"))
            balanceService.createBalance(user, 0L, "CR");
    }

    public List<AccountHolder> getAllAccountHolders() {
        return accountHolderRepo.findAll();
    }


    public AccountHolder updateAccountHolder(String username, AccountHolder accountHolder) {
        AccountHolder foundUser = accountHolderRepo.findByUsername(username).orElse(null);

        foundUser.setEmail(accountHolder.getEmail());
        foundUser.setAddress(accountHolder.getAddress());

        return accountHolderRepo.save(foundUser);
    }


    public AccountHolder getAccountHolderById(Long id) {
        return accountHolderRepo.findById(id).orElse(null);
    }

    public List<String> getRolesByUsername(String username) {
        return accountHolderRepo.getRolesByUsername(username);
    }

    public ResponseEntity<AccountHolder> getAccountHolderByUsername(String name) {
        Optional<AccountHolder> foundUser = accountHolderRepo.getAccountHolderByUsername(name);
        return foundUser.map(ResponseEntity::ok).orElse(null);
    }

    public ResponseEntity<Boolean> deleteAccountHolderByUsername(String username) {

        AccountHolder foundUser = accountHolderRepo.findByUsername(username).orElse(null);
        if(foundUser != null){
            accountHolderRepo.deleteById(foundUser.getId());
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.badRequest().body(false);
    }
}
