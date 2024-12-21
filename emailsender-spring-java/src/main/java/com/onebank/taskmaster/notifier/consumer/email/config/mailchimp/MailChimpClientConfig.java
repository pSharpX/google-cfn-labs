package com.onebank.taskmaster.notifier.consumer.email.config.mailchimp;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailChimpEnabled;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConditionalOnMailChimpEnabled
@EnableFeignClients(value = "com.onebank.taskmaster.notifier.consumer.email.provider.mailchimp")
public class MailChimpClientConfig {

	@Bean(name = "mailchimpProperties")
	@ConfigurationProperties(prefix="task-master.channel.email.mailchimp")
	public Properties mailchimpProperties() {
		return new Properties();
	}

	@Bean
	public RequestInterceptor basicAuthRequestInterceptor(@Qualifier("mailchimpProperties") Properties mailchimpProperties) {
		return new MailChimpClientRequestInterceptor(mailchimpProperties.getProperty("apikey"));
	}
}
