package com.rivers.account.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rivers.account.constant.TransactionType;
import com.rivers.account.entity.Account;
import com.rivers.account.exception.AccountNotfoundException;
import com.rivers.account.exception.UnkownTransactionTypeException;
import com.rivers.account.model.AccountPayload;
import com.rivers.account.model.AccountResponse;
import com.rivers.account.model.TransactionPayload;
import com.rivers.account.repository.AccountRepository;
import com.rivers.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account saveAccount(AccountPayload accountPayload) {
		Account account = new Account();
		BeanUtils.copyProperties(accountPayload, account);
		return accountRepository.save(account);
	}

	@Override
	public AccountResponse updateBalance(TransactionPayload transactionPayload) {
		Account account = getAccount(transactionPayload.getAccountNumber());
		TransactionType transactionType = transactionPayload.getType();
		switch (transactionType) {
		case DEPOSIT:
			account.setBalance(account.getBalance() + transactionPayload.getAmount());
			break;
		case WITHDRAW:
			account.setBalance(account.getBalance() - transactionPayload.getAmount());
			break;
		default:
			throw new UnkownTransactionTypeException("Unkown transaction type ' " + transactionPayload.getType() + "'");
		}
		Account updatedAccount = accountRepository.save(account);
		AccountResponse accountResponse = new AccountResponse();
		BeanUtils.copyProperties(updatedAccount, accountResponse);
		return accountResponse;
	}

	@Override
	public Account getAccount(String accountNumber) {
		Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
		return optionalAccount.orElseThrow(() -> new AccountNotfoundException("Account not found!"));
	}

	@Override
	public AccountResponse getBalance(String accountNumber) {
		Account account = getAccount(accountNumber);
		AccountResponse accountResponse = new AccountResponse();
		BeanUtils.copyProperties(account, accountResponse);
		return accountResponse;
	}
}
