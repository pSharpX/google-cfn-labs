package com.onebank.taskmaster.createnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.multibindings.MapBinder;
import com.onebank.taskmaster.createnotification.config.ConfigProvider;
import com.onebank.taskmaster.createnotification.converter.TaskNotificationRequestConverter;
import com.onebank.taskmaster.createnotification.model.NotificationChannel;
import com.onebank.taskmaster.createnotification.model.TaskNotificationType;
import com.onebank.taskmaster.createnotification.notifier.config.EmailNotificationTemplateConfig;
import com.onebank.taskmaster.createnotification.notifier.config.NotificationTemplateConfig;
import com.onebank.taskmaster.createnotification.notifier.config.SmsNotificationTemplateConfig;
import com.onebank.taskmaster.createnotification.service.BuilderResolver;
import com.onebank.taskmaster.createnotification.service.BuilderResolverService;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolver;
import com.onebank.taskmaster.createnotification.service.ContentProviderResolverService;
import com.onebank.taskmaster.createnotification.service.NotificationCreator;
import com.onebank.taskmaster.createnotification.service.NotificationCreatorService;
import com.onebank.taskmaster.createnotification.service.builders.NotificationMessageBuilder;
import com.onebank.taskmaster.createnotification.service.builders.TaskCompletedMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskCompletionReminderMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskCreatedMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.builders.TaskDeletedMessageBuilderService;
import com.onebank.taskmaster.createnotification.service.providers.TaskCompletedContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskCompletionReminderContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskCreatedContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskDeletedContentProvider;
import com.onebank.taskmaster.createnotification.service.providers.TaskMessageContentProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class FunctionConfigModule extends AbstractModule {
    private final ConfigProvider configProvider;

    @Override
    protected void configure() {
        MapBinder<TaskNotificationType, NotificationMessageBuilder> messageBuilderMapBinder = MapBinder.newMapBinder(binder(), TaskNotificationType.class, NotificationMessageBuilder.class);
        messageBuilderMapBinder.addBinding(TaskNotificationType.TASK_CREATED).to(TaskCreatedMessageBuilderService.class).in(Scopes.SINGLETON);
        messageBuilderMapBinder.addBinding(TaskNotificationType.TASK_DELETED).to(TaskDeletedMessageBuilderService.class).in(Scopes.SINGLETON);
        messageBuilderMapBinder.addBinding(TaskNotificationType.TASK_COMPLETED).to(TaskCompletedMessageBuilderService.class).in(Scopes.SINGLETON);
        messageBuilderMapBinder.addBinding(TaskNotificationType.TASK_COMPLETION_REMINDER).to(TaskCompletionReminderMessageBuilderService.class).in(Scopes.SINGLETON);

        MapBinder<TaskNotificationType, TaskMessageContentProvider> contentProviderMapBinder = MapBinder.newMapBinder(binder(), TaskNotificationType.class, TaskMessageContentProvider.class);
        contentProviderMapBinder.addBinding(TaskNotificationType.TASK_CREATED).to(TaskCreatedContentProvider.class).in(Scopes.SINGLETON);
        contentProviderMapBinder.addBinding(TaskNotificationType.TASK_DELETED).to(TaskDeletedContentProvider.class).in(Scopes.SINGLETON);
        contentProviderMapBinder.addBinding(TaskNotificationType.TASK_COMPLETED).to(TaskCompletedContentProvider.class).in(Scopes.SINGLETON);
        contentProviderMapBinder.addBinding(TaskNotificationType.TASK_COMPLETION_REMINDER).to(TaskCompletionReminderContentProvider.class).in(Scopes.SINGLETON);

        MapBinder<NotificationChannel, NotificationTemplateConfig> templateConfigProviderMapBinder = MapBinder.newMapBinder(binder(), NotificationChannel.class, NotificationTemplateConfig.class);
        templateConfigProviderMapBinder.addBinding(NotificationChannel.EMAIL).toInstance(configProvider.getConfig(EmailNotificationTemplateConfig.class));
        templateConfigProviderMapBinder.addBinding(NotificationChannel.SMS).toInstance(configProvider.getConfig(SmsNotificationTemplateConfig.class));

        bind(BuilderResolver.class).to(BuilderResolverService.class).in(Scopes.SINGLETON);
        bind(ContentProviderResolver.class).to(ContentProviderResolverService.class).in(Scopes.SINGLETON);
        bind(NotificationCreator.class).to(NotificationCreatorService.class).in(Scopes.SINGLETON);
        bind(TaskNotificationRequestConverter.class).toInstance(new TaskNotificationRequestConverter());
    }
}
