package com.reloadly.accountingservice;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AccountingServiceApplication {
	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		return mapper;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountingServiceApplication.class, args);
	}

}
