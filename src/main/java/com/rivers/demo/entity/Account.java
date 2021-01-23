package com.rivers.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.sql.Timestamp;

@Entity(name = "Account")
@Data
@Builder
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "account_number",unique = true)
    private String accountNumber;
    @Column(name = "last_update_timestamp")
    private Timestamp lastUpdateTimestamp;
    @Column(name = "balance")
    private Double balance;
}
