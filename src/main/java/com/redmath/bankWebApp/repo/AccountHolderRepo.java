package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByUsername(String username);
}
