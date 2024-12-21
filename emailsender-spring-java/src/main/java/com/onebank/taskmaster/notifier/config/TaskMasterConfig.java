package com.onebank.taskmaster.notifier.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnNotificationEnabled
@EnableNotifier
public class TaskMasterConfig {
}
