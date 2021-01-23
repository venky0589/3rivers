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

	@Query("{'transactionTs': {$gte: ?0, $lte: ?1}}")
	Page<Transaction> findTransactions(LocalDateTime from, LocalDateTime to, Pageable pageable);

//	@Query("{'transactionTs': {$gte: ?0}}")
	@Query("{$and : [{'transactionTs': {$gte: ?0}}, {'type': {$in: ?1}}]}")
	Page<Transaction> findTransactions(LocalDate from, List<String> transactionType, Pageable pageable);
}