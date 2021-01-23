package com.rivers.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rivers.demo.exception.AccountNotfoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BaseController {

    @ExceptionHandler(value = AccountNotfoundException.class)
    public ResponseEntity<Object> exception(AccountNotfoundException exception) {
        ObjectMapper mapper=new ObjectMapper();
        JsonNode obj = null;
        try {
            obj=mapper.readTree("{\"message\":\"Account not found\"}");
        }catch(Exception e){
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
    }
}
