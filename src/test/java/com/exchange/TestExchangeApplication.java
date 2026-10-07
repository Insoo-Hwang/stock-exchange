package com.exchange;

import org.springframework.boot.SpringApplication;

/**
 * Docker Compose 없이 Testcontainers PostgreSQL로 앱을 띄운다 (IDE에서 실행).
 */
public class TestExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.from(ExchangeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
