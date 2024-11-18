package com.onebank.taskmaster.searchtask.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

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

    public static <T> T convertTo(Class<T> type, Map<String, Object> map) throws IOException {
        return objectMapper.convertValue(map, type);
    }

    public static <T> T convertTo(Class<T> type, byte[] content) throws IOException {
        return objectMapper.readValue(content, type);
    }

    public static String toJson(Object content) throws IOException {
        return objectMapper.writeValueAsString(content);
    }
}
