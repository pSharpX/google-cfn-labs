package com.onebank.taskmaster.notifier.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class EmailNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationApplication.class, args);
	}

}