package com.rivers.transaction.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.rivers.transaction.model.TransactionPayload;
import com.rivers.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {
	@Autowired
	private TransactionService transactionService;

	@KafkaListener(topics = "feed_transaction", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
	public void transactionListener(TransactionPayload transactionPayload) {
		log.debug(getClass().getName() + "#transactionListener(TransactionPayload transactionPayload)");
		log.debug(transactionPayload.toString());
		transactionService.saveTransaction(transactionPayload);
	}

}
