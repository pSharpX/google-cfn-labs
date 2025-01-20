package com.onebank.taskmaster.createnotification.service;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.entity.NotificationEntity;
import com.onebank.taskmaster.createnotification.model.TaskNotificationRequest;
import com.onebank.taskmaster.createnotification.model.senders.NotificationMessage;
import com.onebank.taskmaster.createnotification.repository.NotificationRepository;
import com.onebank.taskmaster.createnotification.service.producers.NotificationMessageProducer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
@SuppressWarnings("unchecked")
public class NotificationCreatorService implements NotificationCreator {
    private final NotificationRepository notificationRepository;
    private final BuilderResolver builderResolver;
    private final NotificationMessageProducer notificationMessageProducer;
    //private final ConvertTo<TaskNotificationRequest, NotificationEntity> converter;

    @Override
    public void create(@NonNull TaskNotificationRequest request) {
        log.debug("Creating new notification with title [{}]", request.getTitle());
        NotificationEntity notificationEntity = new NotificationEntity();
        // = notificationRepository.save(converter.convert(request));
        request.setId(notificationEntity.getId());

        NotificationMessage notification = builderResolver.resolve(notificationEntity.getNotificationType()).build(request);
        notificationMessageProducer.sendMessage(notification);
    }
}
