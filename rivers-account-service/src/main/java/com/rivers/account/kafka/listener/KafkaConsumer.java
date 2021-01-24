package com.rivers.account.kafka.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.rivers.account.model.AccountPayload;
import com.rivers.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {
	@Autowired
	private AccountService accountService;

	@KafkaListener(topics = "feed_account", groupId = "group_account", containerFactory = "accountKafkaListenerFactory")
	public void accountListener(AccountPayload accountPayload) {
		log.debug(getClass().getName() + "#accountListener(AccountPayload accountPayload)");
		log.debug(accountPayload.toString());
		accountService.saveAccount(accountPayload);
	}
}
