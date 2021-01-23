package com.rivers.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rivers.demo.entity.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByAccountNumber(String accountNumber);
}
