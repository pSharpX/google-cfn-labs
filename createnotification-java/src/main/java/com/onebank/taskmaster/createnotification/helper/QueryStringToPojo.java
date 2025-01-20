package com.onebank.taskmaster.createnotification.helper;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@UtilityClass
public class QueryStringToPojo {
    public static <T> T convertTo(String queryString, Class<T> clazz) {
        try {
            // Create a new instance of the POJO
            T pojo = clazz.getDeclaredConstructor().newInstance();

            // Parse the query string into a map
            Map<String, List<String>> queryMap = parseQueryString(queryString);

            // Use reflection to set fields in the POJO
            for (Map.Entry<String, List<String>> entry : queryMap.entrySet()) {
                String fieldName = entry.getKey();
                List<String> values = entry.getValue();

                Field field;
                try {
                    field = clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                    // Skip fields that don't exist in the POJO
                    continue;
                }

                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                if (fieldType.isArray()) {
                    // Handle array fields
                    Class<?> componentType = fieldType.getComponentType();
                    Object array = Array.newInstance(componentType, values.size());
                    for (int i = 0; i < values.size(); i++) {
                        Array.set(array, i, convertValue(values.get(i), componentType));
                    }
                    field.set(pojo, array);
                } else if (Collection.class.isAssignableFrom(fieldType)) {
                    // Handle collection fields
                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType parameterizedType) {
                        Class<?> collectionType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                        Collection<Object> collection = createCollectionInstance(fieldType);
                        for (String value : values) {
                            collection.add(convertValue(value, collectionType));
                        }
                        field.set(pojo, collection);
                    }
                } else if (!values.isEmpty()) {
                    // Handle single-value fields
                    field.set(pojo, convertValue(values.get(0), fieldType));
                }
            }

            return pojo;
        } catch (Exception e) {
            throw new RuntimeException("Error converting query string to POJO", e);
        }
    }

    private static Map<String, List<String>> parseQueryString(String queryString) {
        Map<String, List<String>> queryMap = new HashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
            String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
            queryMap.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
        }
        return queryMap;
    }

    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return value;
        }
    }

    private static Collection<Object> createCollectionInstance(Class<?> fieldType) {
        if (fieldType.isAssignableFrom(List.class)) {
            return new ArrayList<>();
        } else if (fieldType.isAssignableFrom(Set.class)) {
            return new HashSet<>();
        } else {
            throw new RuntimeException("Unsupported collection type: " + fieldType);
        }
    }
}
