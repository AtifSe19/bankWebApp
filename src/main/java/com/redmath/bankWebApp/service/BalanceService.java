package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.AccountHolder;
import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.repo.BalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class BalanceService {
    private final BalanceRepo balanceRepo;
    @Autowired
    public BalanceService(BalanceRepo balanceRepo){
        this.balanceRepo = balanceRepo;
    }

    public List<Balance> findBalanceByAccountId(Long id){
        return balanceRepo.findBalanceByAccountHolderId(id);
    }

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

        bal.setAccountHolder(user);
        balanceRepo.save(bal);
    }

    public List<Balance> viewBalanceHistory() {
        return balanceRepo.findAll();
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
}
