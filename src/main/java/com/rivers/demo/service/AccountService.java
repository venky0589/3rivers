package com.rivers.demo.service;

import com.rivers.demo.entity.Account;
import com.rivers.demo.entity.Transaction;

public interface AccountService {
    void saveAccount(Account account);

    void updateBalance(Transaction ts);
}
