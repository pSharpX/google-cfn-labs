package com.onebank.taskmaster.templatemanager.service.manager;

import com.onebank.taskmaster.templatemanager.model.SubEventType;

public interface ActionTemplateResolver {
    ActionTemplate resolve(SubEventType subEventType);
}
