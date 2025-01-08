package com.onebank.taskmaster.templatemanager.service.storage;

import com.google.cloud.storage.Storage;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
@Slf4j
public class DefaultGetObjectContentService implements GetObjectContent {
    private final Storage storageService;

    @Override
    public String execute(String name, String bucket) {
        log.debug("Fetching object content from bucket: OBJECT_NAME = {},  BUCKET = {}", name, bucket);
        return new String(storageService.readAllBytes(bucket, name), StandardCharsets.UTF_8);
    }
}
