package com.redmath.bankWebApp.repo;

import com.redmath.bankWebApp.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByUsername(String username);

    Optional<AccountHolder> findAccountHolderByUsername(String username);


    @Query(value = "SELECT roles FROM account_holder WHERE username = ?1", nativeQuery = true)
    List<String> getRolesByUsername(String username);

    @Query(value = "SELECT * FROM account_holder WHERE username = ?1", nativeQuery = true)
    Optional<AccountHolder> getAccountHolderByUsername(String name);





//    delete founderUser from account_holder table

    @Query(value = "DELETE acc FROM account_holder acc WHERE acc = ?")
    void deleteAccountHolderByUsername(AccountHolder foundUser);
}
