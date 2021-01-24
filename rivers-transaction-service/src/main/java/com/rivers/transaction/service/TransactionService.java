package com.rivers.transaction.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rivers.transaction.constant.StatementDurationType;
import com.rivers.transaction.entity.Transaction;
import com.rivers.transaction.model.TransactionPayload;

public interface TransactionService {
	void saveTransaction(TransactionPayload transactionPayload);

	Page<Transaction> getTransactions(String accountNumber, LocalDate from, LocalDate to, List<String> transactionType, Pageable pageable);

	Page<Transaction> getTransactions(String accountNumber, long statementDuration,
			StatementDurationType statementDurationType, List<String> transactionType, Pageable pageable);
}
