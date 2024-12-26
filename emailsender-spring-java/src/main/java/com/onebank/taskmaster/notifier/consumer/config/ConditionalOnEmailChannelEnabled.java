package com.onebank.taskmaster.notifier.consumer.config;

import com.onebank.taskmaster.notifier.config.ConditionalOnNotificationEnabled;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ConditionalOnNotificationEnabled
@ConditionalOnProperty(name = "task-master.notification.consumer.channel", havingValue = "email")
public @interface ConditionalOnEmailChannelEnabled {
}
