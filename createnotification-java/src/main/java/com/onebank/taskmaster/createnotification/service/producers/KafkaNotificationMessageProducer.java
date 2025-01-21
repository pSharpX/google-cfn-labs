package com.onebank.taskmaster.createnotification.service.producers;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.helper.FunctionUtils;
import com.onebank.taskmaster.createnotification.model.AbstractMessage;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.notifier.config.KafkaConfigProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class KafkaNotificationMessageProducer implements NotificationMessageProducer {
    private final KafkaProducer<String, AbstractMessage> kafkaProducer;
    private final KafkaConfigProperties config;

    @Override
    public void sendMessage(@NonNull NotificationMessage notification) {
        log.debug("Sending [channel={}, type={}] message through Kafka Provider", notification.getChannel(), notification.getType());
        ProducerRecord<String, AbstractMessage> record = new ProducerRecord<>(config.getTopic(), notification);
        FunctionUtils.supplyAsync(kafkaProducer.send(record))
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.debug("Message sent [{}] with offset=[{}]", notification, result.offset());
                    } else {
                        log.debug("Unable to send message=[{}] due to : {}", notification, ex.getMessage());
                    }
                });
    }
}
