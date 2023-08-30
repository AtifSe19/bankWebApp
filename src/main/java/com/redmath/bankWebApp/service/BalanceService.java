package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.repo.BalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
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

    public void createBalance(Balance balance) {
       balance.setDate(LocalDateTime.now());
        balanceRepo.save(balance);
    }

    public List<Balance> getAllBalances() {
        return balanceRepo.findAll();
    }
}
