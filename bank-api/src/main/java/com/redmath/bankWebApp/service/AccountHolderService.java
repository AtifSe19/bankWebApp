package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.repo.AccountHolderRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountHolderService implements UserDetailsService {
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

    public AccountHolder findByUsername(String username){
        return accountHolderRepo.findByUsername(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountHolder accHolder = findByUsername(username);
        if(accHolder == null){
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
