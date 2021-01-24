package com.rivers.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivers.account.model.AccountResponse;
import com.rivers.account.model.TransactionPayload;
import com.rivers.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@GetMapping("/check-balance/{accountNumber}")
	public ResponseEntity<AccountResponse> getBalance(@PathVariable String accountNumber) {
		log.debug(getClass().getName() + "#getBalance(@PathVariable String accountNumber)");
		return ResponseEntity.ok(accountService.getBalance(accountNumber));
	}

	@PostMapping("/update-balance")
	public ResponseEntity<AccountResponse> updateBalance(@RequestBody TransactionPayload transactionPayload) {
		log.debug(getClass().getName() + "#updateBalance(@RequestBody TransactionPayload transactionPayload)");
		return ResponseEntity.ok(accountService.updateBalance(transactionPayload));
	}
}
