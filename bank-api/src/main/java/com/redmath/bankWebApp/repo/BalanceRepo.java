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

    @Query(value = "SELECT * FROM balance WHERE fk_account_holder_id = (SELECT id FROM account_holder WHERE username = ?1)", nativeQuery = true)
    List<Balance> findBalanceHistoryByUsername(String username);

//    write a query to get username from balance by using the foreign key fk_account_holder_id in the balance table
    @Query(value = "SELECT username FROM account_holder WHERE id = (SELECT fk_account_holder_id FROM balance WHERE id = ?1)", nativeQuery = true)
    Balance findUsernameByAccountHolderId(Long id);
}
