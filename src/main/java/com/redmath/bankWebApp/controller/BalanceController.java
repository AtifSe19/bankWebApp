package com.redmath.bankWebApp.controller;

import com.redmath.bankWebApp.model.Balance;
import com.redmath.bankWebApp.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/{id}")
    public List<Balance> findBalanceByAccountId(@PathVariable Long id){
        return balanceService.findBalanceByAccountId(id);
    }

//    @PostMapping
//    public void createBalance(@RequestBody Balance balance){
//        balanceService.createBalance(balance);
//    }

//    @PutMapping("/update/{id}")
//    public Balance updateBalance(@PathVariable Long id, @RequestBody Balance balance){
//        return balanceService.updateBalance(id, balance);
//    }

    @GetMapping
    public List<Balance> viewBalanceHistory(){
        return balanceService.viewBalanceHistory();
    }


}
