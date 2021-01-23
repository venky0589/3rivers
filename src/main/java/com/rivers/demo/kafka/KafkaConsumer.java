package com.rivers.demo.kafka;

import java.util.List;


import com.rivers.demo.entity.Account;
import com.rivers.demo.entity.Transaction;
import com.rivers.demo.model.TransactionPayload;
import com.rivers.demo.repository.UserRepository;
import com.rivers.demo.service.AccountService;
import com.rivers.demo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @KafkaListener(topics = "feed_account", groupId = "group_account", containerFactory = "accountKafkaListenerFactory")
    public void accountListener(Account account) {
        log.debug("Consumed JSON message: " + account);
        accountService.saveAccount(account);
    }

    @KafkaListener(topics = "feed_transaction", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
    public void transactionListener(TransactionPayload transaction) {
        log.debug("Consumed JSON message: " + transaction);
        transactionService.saveTransaction(transaction);
    }

}