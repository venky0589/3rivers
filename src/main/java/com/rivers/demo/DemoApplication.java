package com.rivers.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import com.rivers.demo.entity.Transaction;
import com.rivers.demo.repository.TransactionRepository;
import com.rivers.demo.service.TransactionService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	@Autowired
	private TransactionRepository transactionRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user=new User();
//		user.setName("Name");
//		user.setDept("Dept");
//		user.setOffset(120L);
//		user.setSalary(12345L);
//		ObjectMapper mapper=new ObjectMapper();
//		String str=mapper.writeValueAsString(user);
//		System.out.println(str);
//		java.sql.Timestamp sqlTS = java.sql.Timestamp.valueOf("2021-01-07 21:30:55.888");
//		java.sql.Timestamp transTs = java.sql.Timestamp.valueOf("2021-01-08 21:30:55.888");
//
//		Account acc=Account.builder().accountNumber("test123").balance(20.0).lastUpdateTimestamp(sqlTS).build();
//		Transaction ts=Transaction.builder().accountNumber("test123").amount(5.0).transactionTs(transTs).build();
//		System.out.println(mapper.writeValueAsString(acc));
//		System.out.println(mapper.writeValueAsString(ts));
	}
}
