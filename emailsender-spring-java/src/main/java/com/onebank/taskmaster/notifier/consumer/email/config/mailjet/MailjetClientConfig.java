package com.onebank.taskmaster.notifier.consumer.email.config.mailjet;

import com.onebank.taskmaster.notifier.consumer.email.config.ConditionalOnMailjetEnabled;
import com.onebank.taskmaster.notifier.consumer.email.config.mailchimp.MailChimpClientRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConditionalOnMailjetEnabled
@EnableFeignClients(value = "com.onebank.taskmaster.notifier.consumer.email.provider.mailjet")
public class MailjetClientConfig {

	@Bean(name = "mailjetProperties")
	@ConfigurationProperties(prefix="task-master.channel.email.mailjet")
	public Properties mailjetProperties() {
		return new Properties();
	}

	@Bean
	public RequestInterceptor basicAuthRequestInterceptor(@Qualifier("mailjetProperties") Properties properties) {
		return new MailChimpClientRequestInterceptor(properties.getProperty("apikey"));
	}
}
