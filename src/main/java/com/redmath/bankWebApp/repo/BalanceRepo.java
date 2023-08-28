package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {

    List<Balance> findBalanceByAccountHolderId(Long id);
}
