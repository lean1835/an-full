package com.vti.springdatajpa.repository;

import com.vti.springdatajpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaRepository<Account, Integer>
        , JpaSpecificationExecutor<Account> {

    Account findByUsername(String username);
}
