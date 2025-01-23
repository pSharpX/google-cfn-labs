package com.onebank.taskmaster.createnotification.notifier.config;

import com.onebank.taskmaster.createnotification.config.PropertiesPrefix;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.notification.pubsub")
public final class PubSubConfigProperties {
    private String projectId;
    private String locationId;
    private String topic;
    private String subscriptionId;
    private String serviceAccountKeyPath;
}
