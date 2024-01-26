package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@ComponentScan(basePackages = "com")
public class KafkaTransactionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTransactionDemoApplication.class, args);
	}

}
