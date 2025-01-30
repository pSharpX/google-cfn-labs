package com.onebank.taskmaster.createnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.converter.TaskNotificationRequestConverter;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.repository.NotificationPreferenceRepository;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("unchecked")
public class NotificationCreatorService implements NotificationCreator {
    private final NotificationRepository notificationRepository;
    private final NotificationPreferenceRepository notificationPreferenceRepository;
    private final BuilderResolver builderResolver;
    private final NotificationMessageProducer notificationMessageProducer;
    private final TaskNotificationRequestConverter converter;

    @Override
    public void create(@NonNull TaskNotificationRequest request) {
        log.debug("Retrieving notification preferences for user = {}", request.getUser());
        Set<NotificationChannel> channels = notificationPreferenceRepository.findByUserIdentifier(request.getUser())
                .map(notificationPreference -> {
                    Set<NotificationChannel> allowedChannels = new HashSet<>();
                    if (notificationPreference.isEmailEnabled()) {
                        allowedChannels.add(NotificationChannel.EMAIL);
                    }
                    if (notificationPreference.isSmsEnabled()) {
                        allowedChannels.add(NotificationChannel.SMS);
                    }
                    if (notificationPreference.isInAppEnabled()) {
                        allowedChannels.add(NotificationChannel.IN_APP);
                    }
                    if (notificationPreference.isPushEnabled()) {
                        allowedChannels.add(NotificationChannel.PUSH);
                    }
                    return allowedChannels;
                }).orElse(Collections.emptySet());

        if (channels.isEmpty()) {
            log.debug("Notifications are turned off for user = {}", request.getUser());
            return;
        }
        channels.forEach(channel -> {
            request.setChannel(channel);
            log.debug("Creating new notification with title = {}, channel = {}", request.getTitle(), request.getChannel());
            NotificationEntity notificationEntity = notificationRepository.save(converter.convert(request));
            request.setId(notificationEntity.getId());

            NotificationMessage notification = builderResolver.resolve(notificationEntity.getNotificationType()).build(request);
            notificationMessageProducer.sendMessage(notification);
        });
    }
}
