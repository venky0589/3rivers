package com.rivers.demo.controller;
import java.util.List;

import com.rivers.demo.entity.User;
import com.rivers.demo.kafka.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rivers.demo.repository.UserRepository;

//@RestController
//@RequestMapping(value = "users")
public class AccountRestController {
    @Autowired
    private KafkaConsumer consumer;

    @GetMapping(path = "/findUserByOffset/{offset}")
    public User getUserByOffset(@PathVariable("offset") Long offset) {
       // return consumer.getUserByOffset(offset);
        return null;
    }

    @GetMapping(path = "/findUsersInRange/{lowerOffset}/{upperOffset}")
    public List<User> getUsersByOffsetRange(@PathVariable("lowerOffset") Long lowerOffset, @PathVariable("upperOffset") Long upperOffset) {
        //return consumer.getUsersByOffsetRange(lowerOffset, upperOffset);
        return null;
    }
}