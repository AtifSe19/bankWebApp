package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }
    @GetMapping("/{id}")
    public List<Balance> findBalanceByAccountId(@PathVariable Long id){
        return balanceService.findBalanceByAccountId(id);
    }


}
