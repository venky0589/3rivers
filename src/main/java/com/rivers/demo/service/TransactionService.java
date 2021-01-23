package com.rivers.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rivers.demo.constant.StatementDurationType;
import com.rivers.demo.entity.Transaction;
import com.rivers.demo.model.TransactionPayload;

public interface TransactionService {
	void saveTransaction(TransactionPayload transactionPayload);

	Page<Transaction> getTransactions(String accountNumber, LocalDate from, LocalDate to, Pageable pageable);

	Page<Transaction> getTransactions(String accountNumber, long statementDuration,
			StatementDurationType statementDurationType, List<String> transactionType, Pageable pageable);
}
