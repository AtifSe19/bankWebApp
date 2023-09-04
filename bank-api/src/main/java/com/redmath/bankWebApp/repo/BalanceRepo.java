package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {

    @Query(value = "SELECT * FROM balance WHERE fk_account_holder_id = (SELECT id FROM account_holder WHERE username = ?1) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Balance findBalanceByAccountHolderUsername(String username);

    @Query(value = "SELECT * FROM balance WHERE account_holder_id = (SELECT id FROM account_holder WHERE username = ?1)", nativeQuery = true)
    List<Balance> findBalanceHistoryByUsername(String username);
}
