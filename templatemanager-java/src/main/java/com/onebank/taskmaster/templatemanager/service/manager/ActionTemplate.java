package com.onebank.taskmaster.templatemanager.service.manager;

import com.google.events.cloud.storage.v1.StorageObjectData;

public interface ActionTemplate {
    void execute(StorageObjectData objectData);
}
