package com.onebank.taskmaster.createnotification.service.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.inject.Inject;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.onebank.taskmaster.createnotification.helper.FunctionUtils;
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
        log.debug("Sending [channel={}, type={}] message through PubSub Provider", notification.getChannel(), notification.getType());
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                .setData(ByteString.copyFromUtf8(FunctionUtils.toJson(objectMapper, notification)))
                .putAttributes(TYPE_ATTRIBUTE, notification.getChannel().getName())
                .build();
        publisher.publish(pubsubMessage);
    }
}
