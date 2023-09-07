package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/balance")
@CrossOrigin("https://localhost:3000")
public class BalanceController {
    private final BalanceService balanceService;
    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }
    @GetMapping("/history")
    @PreAuthorize("hasAuthority('USER')")
    public List<Balance> findBalanceHistoryByUsername(Authentication auth){
        return balanceService.findBalanceHistoryByUsername(auth.getName());
    }
    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public Balance showBalance(Authentication auth){
        return balanceService.showBalance(auth.getName());
    }
}
