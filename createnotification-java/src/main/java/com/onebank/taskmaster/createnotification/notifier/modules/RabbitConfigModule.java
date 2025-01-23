package com.onebank.taskmaster.createnotification.notifier.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.onebank.taskmaster.createnotification.config.AppConfigProperties;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.notifier.config.RabbitConfigProperties;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import com.onebank.taskmaster.createnotification.service.producers.RabbitNotificationMessageProducer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RequiredArgsConstructor
public class RabbitConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NotificationMessageProducer.class).to(RabbitNotificationMessageProducer.class).in(Scopes.SINGLETON);
    }

    @Provides
    @Singleton
    public RabbitConfigProperties rabbitConfigProperties(final ConfigProvider configProvider) {
        return configProvider.getConfig(RabbitConfigProperties.class);
    }

    @Provides
    @Singleton
    public ConnectionFactory connectionFactory(final RabbitConfigProperties configProperties) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(configProperties.getHostname());
        connectionFactory.setPort(configProperties.getPort());
        connectionFactory.setVirtualHost(configProperties.getVirtualhost());
        connectionFactory.setUsername(configProperties.getUsername());
        connectionFactory.setPassword(configProperties.getPassword());
        return connectionFactory;
    }

    @Provides
    @Singleton
    public Connection defaultConnection(final ConnectionFactory connectionFactory) throws IOException, TimeoutException {
        return connectionFactory.newConnection();
    }

    @Provides
    @Singleton
    public Channel defaultChannel(
            final Connection connection,
            final AppConfigProperties appConfigProperties,
            final RabbitConfigProperties configProperties) throws IOException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(configProperties.getTopic(), "topic");
        if (appConfigProperties.getNotification().isProvisioned()) {
            AMQP.Queue.DeclareOk declaredQueue = channel.queueDeclare(configProperties.getTopic(), true, false, false, null);
            channel.queueBind(declaredQueue.getQueue(), configProperties.getTopic(), configProperties.getRoutingKey());
        }
        return channel;
    }
}
