package com.rivers.demo.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rivers.demo.constant.StatementDurationType;
import com.rivers.demo.entity.Transaction;
import com.rivers.demo.model.TransactionPayload;
import com.rivers.demo.repository.TransactionRepository;
import com.rivers.demo.service.MongoSequenceService;
import com.rivers.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	private MongoSequenceService mongoSequenceService;
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public void saveTransaction(TransactionPayload transactionPayload) {
		Transaction transaction = new Transaction();
		long id = mongoSequenceService.getSequenceNumber(Transaction.SEQUENCE_NAME);
		transaction.setId(id);
		BeanUtils.copyProperties(transactionPayload, transaction);
		transactionRepository.save(transaction);
	}

	@Override
	public Page<Transaction> getTransactions(String accountNumber, LocalDate from, LocalDate to, Pageable pageable) {
		LocalDateTime fromLocalDateTime = from.atStartOfDay();
		LocalDateTime toLocalDateTime = to.atTime(23, 59, 59, 999);
		System.out.println("From: " + fromLocalDateTime + " To: " + toLocalDateTime);
		return transactionRepository.findTransactions(accountNumber, fromLocalDateTime, toLocalDateTime, pageable);
	}

	@Override
	public Page<Transaction> getTransactions(String accountNumber, long statementDuration,
			StatementDurationType statementDurationType, List<String> transactionType, Pageable pageable) {
		LocalDate toDay = LocalDate.now();
		LocalDate from;
		// If user passes statementDuration 0 or 1 we get current day/month/year
		// statement
		if (statementDuration < 2) {
			// 0 current day/month/year
			statementDuration = 0;
		} else {
			statementDuration = statementDuration - 1;
		}
		switch (statementDurationType) {
		case DAYS:
			from = toDay.minusDays(statementDuration);
			break;
		case MONTHS:
			LocalDate lastNthMonth = toDay.minusMonths(statementDuration);
			from = LocalDate.of(lastNthMonth.getYear(), lastNthMonth.getMonth(), 01);
			break;
		case YEARS:
			LocalDate lastNthYear = toDay.minusYears(statementDuration);
			from = LocalDate.of(lastNthYear.getYear(), 01, 01);
			break;
		default:
			throw new RuntimeException("Invalid StatementDuration");
		}
		System.out.println("Getting statement from  " + from + " to " + toDay);
		return transactionRepository.findTransactions(accountNumber, from, transactionType, pageable);
	}
}
