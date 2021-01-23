package com.rivers.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDTO {
    @JsonProperty("account_number")
    private String accountNumber;
    private Double balance;
}
