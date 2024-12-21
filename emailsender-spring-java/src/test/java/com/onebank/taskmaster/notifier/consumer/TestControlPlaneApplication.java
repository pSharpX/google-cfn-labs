package com.onebank.taskmaster.notifier.consumer;

import org.springframework.boot.SpringApplication;

public class TestControlPlaneApplication {

	public static void main(String[] args) {
		SpringApplication.from(EmailNotificationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
