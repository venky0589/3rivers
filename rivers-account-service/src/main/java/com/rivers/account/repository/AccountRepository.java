package com.rivers.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rivers.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);
}
