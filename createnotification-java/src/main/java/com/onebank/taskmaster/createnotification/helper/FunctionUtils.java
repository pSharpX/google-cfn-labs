package com.onebank.taskmaster.createnotification.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@UtilityClass
public class FunctionUtils {

    private static final ObjectMapper objectMapper;

    public static final String HTTP_STATUS_CODE = "statusCode";

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T convertTo(Class<T> type, InputStream inputStream) throws IOException {
        return objectMapper.readValue(inputStream, type);
    }

    public static <T> T convertTo(Class<T> type, Map<String, Object> map) {
        return objectMapper.convertValue(map, type);
    }

    public static <T> T convertTo(Class<T> type, byte[] content) throws IOException {
        return objectMapper.readValue(content, type);
    }

    public static String toJson(Object content) throws IOException {
        return objectMapper.writeValueAsString(content);
    }

    public static String toJson(ObjectMapper objectMapper,Object content) {
        try {
            return objectMapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> CompletableFuture<T> supplyAsync(@NonNull Future<T> future) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static <T> byte[] writeValueAsBytes(T data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
