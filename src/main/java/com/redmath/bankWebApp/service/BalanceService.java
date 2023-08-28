package com.redmath.bankWebApp.service;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.repo.BalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
}
