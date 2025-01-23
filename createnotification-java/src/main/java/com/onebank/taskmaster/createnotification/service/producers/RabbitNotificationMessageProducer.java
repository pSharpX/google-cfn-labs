package com.onebank.taskmaster.createnotification.service.producers;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.helper.FunctionUtils;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.notifier.config.RabbitConfigProperties;
import com.rabbitmq.client.Channel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class RabbitNotificationMessageProducer implements NotificationMessageProducer {
    private final Channel channel;
    private final RabbitConfigProperties configProperties;

    @Override
    public void sendMessage(@NonNull NotificationMessage notification) {
        log.debug("Sending [channel={}, type={}] message through Rabbit Provider", notification.getChannel(), notification.getType());
        try {
            channel.basicPublish(configProperties.getTopic(), configProperties.getRoutingKey(), null, FunctionUtils.writeValueAsBytes(notification));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
