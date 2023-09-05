package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByAccountHolderUsername(String username);
}
