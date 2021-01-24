package com.rivers.transaction.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.rivers.transaction.model.AccountResponse;
import com.rivers.transaction.model.TransactionPayload;

@FeignClient(name = "account-feign-client", url= "http://rivers-account-service")
public interface AccountFeignClient {
	
	@PostMapping(value = "/api/v1/account/update-balance", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountResponse> updateBalance(TransactionPayload transactionPayload);
}
