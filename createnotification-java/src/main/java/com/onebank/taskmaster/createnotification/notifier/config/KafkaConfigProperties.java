package com.onebank.taskmaster.createnotification.notifier.config;

import com.onebank.taskmaster.createnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.notification.kafka.producer")
public final class KafkaConfigProperties {
    private String topic;
}
