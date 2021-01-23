package com.rivers.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.rivers.demo.entity.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {

//	@Query("{'transactionTs': {$gte: ?0, $lte: ?1}}")
	@Query("{$and : [{'accountNumber': ?0}, {transactionTs: {$gte: ?1, $lte: ?2}}]}")
	Page<Transaction> findTransactions(String accountNumber, LocalDateTime from, LocalDateTime to, Pageable pageable);

//	@Query("{'transactionTs': {$gte: ?0}}")
	@Query("{$and : [{'accountNumber': ?0}, {'transactionTs': {$gte: ?1}}, {'type': {$in: ?2}}]}")
	Page<Transaction> findTransactions(String accountNumber, LocalDate from, List<String> transactionType,
			Pageable pageable);
}