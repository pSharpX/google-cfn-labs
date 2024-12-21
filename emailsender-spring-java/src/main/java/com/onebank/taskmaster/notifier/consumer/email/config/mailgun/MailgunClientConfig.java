package com.onebank.taskmaster.notifier.consumer.email.config.mailgun;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailgunEnabled;
import com.onebank.taskmaster.notifier.consumer.email.config.mailchimp.MailChimpClientRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConditionalOnMailgunEnabled
@EnableFeignClients(value = "com.onebank.taskmaster.notifier.consumer.email.provider.mailgun")
public class MailgunClientConfig {

	@Bean(name = "mailgunProperties")
	@ConfigurationProperties(prefix="task-master.channel.email.mailgun")
	public Properties mailgunProperties() {
		return new Properties();
	}

	@Bean
	public RequestInterceptor basicAuthRequestInterceptor(@Qualifier("mailgunProperties") Properties properties) {
		return new MailChimpClientRequestInterceptor(properties.getProperty("apikey"));
	}
}
