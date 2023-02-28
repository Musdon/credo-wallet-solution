package com.musdon.simplewalletsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@ComponentScan({"com.musdon.simplewalletsystem"})
@EntityScan({"com.musdon.simplewalletsystem"})
@EnableJpaRepositories({"com.musdon.simplewalletsystem"})

public class SimpleWalletSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleWalletSystemApplication.class, args);
	}

}
