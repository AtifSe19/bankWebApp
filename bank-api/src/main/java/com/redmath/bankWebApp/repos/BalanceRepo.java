package com.redmath.bankWebApp.repos;

import com.redmath.bankWebApp.models.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {

    @Query(value = "SELECT * FROM balances WHERE fk_account_holder_id = (SELECT id FROM account_holders WHERE username = ?1) ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Balance findBalanceByAccountHolderUsername(String username);

    @Query(value = "SELECT * FROM balances WHERE fk_account_holder_id = (SELECT id FROM account_holders WHERE username = ?1)", nativeQuery = true)
    List<Balance> findBalanceHistoryByUsername(String username);

    @Query(value = "SELECT username FROM account_holders WHERE id = (SELECT fk_account_holder_id FROM balances WHERE id = ?1)", nativeQuery = true)
    Balance findUsernameByAccountHolderId(Long id);

}
