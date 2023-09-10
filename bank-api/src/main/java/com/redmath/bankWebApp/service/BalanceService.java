package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.repo.BalanceRepo;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Service
public class BalanceService {
    private final BalanceRepo balanceRepo;
    @Autowired
    public BalanceService(BalanceRepo balanceRepo){
        this.balanceRepo = balanceRepo;
    }

//    public List<Balance> findBalanceByUsername(String username){
//        return balanceRepo.findBalanceByAccountHolderUsername(username);
//    }

    public void createBalance(AccountHolder user, Long amount, String transType) {

        Balance bal = new Balance();

        if(user.getBalances()==null){
            List<Balance> balanceList = new ArrayList<>();
            bal.setAmount(amount);
            balanceList.add(bal);
            user.setBalances(balanceList);
        }
        else {
            int latestBalRecIdx = user.getBalances().size() - 1;
            if (transType.equals("DB")) {
                bal.setAmount(user.getBalances().get(latestBalRecIdx).getAmount() - amount);
            } else {
                bal.setAmount(user.getBalances().get(latestBalRecIdx).getAmount() + amount);
            }
        }

        bal.setDate(LocalDateTime.now());
        bal.setDb_cr_indicator(transType);

        bal.setAccountHolders(user);
        user.getBalances().add(bal);

        balanceRepo.save(bal);
    }

    public List<Balance> findBalanceHistoryByUsername(String username) {
        return balanceRepo.findBalanceHistoryByUsername(username);
    }

    public Balance updateBalance(Balance bal, Long amount, String transType) {

        if(transType.equals("DB")){
            bal.setAmount(bal.getAmount() - amount);
        }
        else{
            bal.setAmount(bal.getAmount() + amount);
        }
        bal.setDb_cr_indicator(transType);
        return balanceRepo.save(bal);
    }

    public Balance showBalance(String username) {
        return balanceRepo.findBalanceByAccountHolderUsername(username);
    }

    public Balance getUsernameByAccountHolderId(Long id) {
        return balanceRepo.findUsernameByAccountHolderId(id);
    }
}
