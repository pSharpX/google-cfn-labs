package com.onebank.taskmaster.createnotification.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@UtilityClass
public class ResourceLoader {
    public static InputStream getResource(String filePath) throws IOException {
        Objects.requireNonNull(filePath, "resource filepath must be non-null");

        return ResourceLoader.class.getClassLoader().getResourceAsStream(filePath);
    }
}
