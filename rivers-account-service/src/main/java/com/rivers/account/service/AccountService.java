package com.rivers.account.service;

import com.rivers.account.entity.Account;
import com.rivers.account.model.AccountPayload;
import com.rivers.account.model.AccountResponse;
import com.rivers.account.model.TransactionPayload;

public interface AccountService {
	Account saveAccount(AccountPayload accountPayload);

	AccountResponse updateBalance(TransactionPayload transactionPayload);

	Account getAccount(String accountNumber);

	AccountResponse getBalance(String accountNumber);
}
