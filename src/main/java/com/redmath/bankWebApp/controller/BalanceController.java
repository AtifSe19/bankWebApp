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
public class BalanceController {

    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }
//    @GetMapping("/{username}")
//    @PreAuthorize("hasRole('USER')")
//    public List<Balance> findBalanceByUsername(@PathVariable String username){
//        return balanceService.findBalanceByUsername(username);
//    }

    @GetMapping("/history/{username}")
    @PreAuthorize("hasAuthority('USER')")
    public List<Balance> findBalanceHistoryByUsername(@PathVariable String username){
        return balanceService.findBalanceHistoryByUsername(username);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public Balance showBalance(Authentication auth){
        return balanceService.showBalance(auth);
    }
}
