package com.rivers.demo.controller;
import java.util.List;

import com.rivers.demo.dao.Account;
import com.rivers.demo.model.AccountResponseDTO;
import com.rivers.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/account")
@Slf4j
public class AccountRestController extends BaseController{
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/balance/{accountNumber}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponseDTO> getBalance(@PathVariable("accountNumber") String accountNumber) {
        log.debug("In AccountRestController getBalance");
        // return consumer.getUserByOffset(offset);
        Account account =accountService.getBalance(accountNumber);

        return ResponseEntity.ok(AccountResponseDTO.builder().accountNumber(accountNumber).balance(account.getBalance()).build());
    }
}
