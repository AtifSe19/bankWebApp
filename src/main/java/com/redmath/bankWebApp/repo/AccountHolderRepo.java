package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

}
