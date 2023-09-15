package com.redmath.bankWebApp.repos;

import com.redmath.bankWebApp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByAccountHoldersUsername(String username);
}
