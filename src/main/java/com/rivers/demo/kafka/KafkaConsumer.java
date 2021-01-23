package com.rivers.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.rivers.demo.entity.Account;
import com.rivers.demo.model.TransactionPayload;
import com.rivers.demo.service.AccountService;
import com.rivers.demo.service.TransactionService;


//@Service
public class KafkaConsumer {
   /* @Autowired
    UserRepository userRepository;

    @KafkaListener(topics = "" +
            "", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void consumeJson(User user) {
        System.out.println("Consumed JSON message: " + user);
        userRepository.save(user);
    }*/

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @KafkaListener(topics = "feed_account", groupId = "group_account", containerFactory = "accountKafkaListenerFactory")
    public void accountListener(Account account) {
        System.out.println("Consumed JSON message: " + account);
        accountService.saveAccount(account);
    }

    @KafkaListener(topics = "feed_transaction", groupId = "group_transaction", containerFactory = "transactionKafkaListenerFactory")
    public void TransactionListener(TransactionPayload transactionPayload) {
        System.out.println("Consumed JSON message: " + transactionPayload);
        //userRepository.save(user);
        transactionService.saveTransaction(transactionPayload);
    }

    /*public User getUserByOffset(Long offset) {
        return userRepository.findByOffset(offset);
    }

    public List<User> getUsersByOffsetRange(Long lowerOffset, Long upperOffset) {
        return userRepository.findInOffsetRange(lowerOffset, upperOffset);
    }*/
}