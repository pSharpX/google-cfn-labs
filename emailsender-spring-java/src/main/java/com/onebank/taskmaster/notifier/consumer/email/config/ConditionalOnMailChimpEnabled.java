package com.onebank.taskmaster.notifier.consumer.email.config;

import com.onebank.taskmaster.notifier.consumer.config.ConditionalOnEmailChannelEnabled;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnEmailChannelEnabled
@ConditionalOnProperty(name = "task-master.notification.consumer.provider", havingValue = "mailchimp")
public @interface ConditionalOnMailChimpEnabled {
}
