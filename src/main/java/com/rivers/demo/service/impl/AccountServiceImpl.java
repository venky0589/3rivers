package com.rivers.demo.service.impl;

import com.rivers.demo.constant.TransactionType;
import com.rivers.demo.entity.Account;
import com.rivers.demo.entity.Transaction;
import com.rivers.demo.repository.AccountRepository;
import com.rivers.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void saveAccount(Account account) {
		accountRepository.save(account);
	}

	@Override
	public void updateBalance(Transaction ts) {
		List<Account> acc = accountRepository.findByAccountNumber(ts.getAccountNumber());
		if (!CollectionUtils.isEmpty(acc)) {
			Account account = acc.get(0);
			if (ts.getType() == TransactionType.DEPOSIT) {
				account.setBalance(account.getBalance() + ts.getAmount());
			} else if (ts.getType() == TransactionType.WITHDRAW) {
				account.setBalance(account.getBalance() - ts.getAmount());
			}
			account.setLastUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
			accountRepository.save(account);
		}

	}
}
