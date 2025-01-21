package com.onebank.taskmaster.createnotification.service.producers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.inject.Inject;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class PubSubNotificationMessageProducer implements NotificationMessageProducer {
    private final Publisher publisher;
    private final ObjectMapper objectMapper;

    private static final String TYPE_ATTRIBUTE = "type";

    @Override
    public void sendMessage(@NonNull NotificationMessage notification) {
        try {
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(ByteString.copyFromUtf8(objectMapper.writeValueAsString(notification)))
                    .putAttributes(TYPE_ATTRIBUTE, notification.getChannel().getName())
                    .build();
            publisher.publish(pubsubMessage);
        } catch (JsonProcessingException e) {
            log.error("Error occurred while publishing message");
            throw new RuntimeException(e);
        }
    }
}
