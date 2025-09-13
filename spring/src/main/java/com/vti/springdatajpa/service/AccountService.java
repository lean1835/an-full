package com.vti.springdatajpa.service;

import com.vti.springdatajpa.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    public List<Account> getAllAccounts();

    public Account getAccountById(int id);

    public Page<Account> getAllAccounts(Pageable pageable, String search);

    public Account register(String username, String rawPassword);
}
