package com.onebank.taskmaster.notifier.consumer.email.config.sendgrid;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnSendGridEnabled;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConditionalOnSendGridEnabled
@EnableFeignClients(value = "com.onebank.taskmaster.notifier.consumer.email.provider.sendgrid")
public class SendGridClientConfig {

	@Bean(name = "sendgridProperties")
	@ConfigurationProperties(prefix="task-master.channel.email.sendgrid")
	public Properties sendgridProperties() {
		return new Properties();
	}

	@Bean
	public RequestInterceptor basicAuthRequestInterceptor(@Qualifier("sendgridProperties") Properties properties) {
		return new SendGridClientRequestInterceptor(properties.getProperty("apikey"));
	}
}
