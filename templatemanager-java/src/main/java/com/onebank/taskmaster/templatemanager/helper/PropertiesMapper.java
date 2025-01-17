package com.onebank.taskmaster.templatemanager.helper;

import com.onebank.taskmaster.templatemanager.config.PropertiesPrefix;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@UtilityClass
public class PropertiesMapper {
    /**
     * Maps a Properties object to a generic POJO
     * @param properties the properties object
     * @param clazz the class type of the target POJO
     * @return the mapped POJO
     */
    public static <T> T mapPropertiesToPojo(Properties properties, Class<T> clazz) {
        try {
            // Create an instance of the POJO
            T pojo = clazz.getDeclaredConstructor().newInstance();
            // Check for a prefix annotation
            String prefix = "";
            if (clazz.isAnnotationPresent(PropertiesPrefix.class)) {
                PropertiesPrefix annotation = clazz.getAnnotation(PropertiesPrefix.class);
                prefix = !annotation.value().trim().isEmpty() ? annotation.value().trim() + "." : "";
            }

            // Iterate through all fields of the class
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Make the field accessible

                String fieldName = field.getName();
                String propertyName = prefix + fieldName;

                if (isPrimitiveOrWrapper(field.getType()) || field.getType() == String.class) {
                    // Handle primitive or basic field types
                    String propertyValue = properties.getProperty(propertyName);
                    if (propertyValue != null) {
                        Object value = convertValue(propertyValue, field.getType());
                        field.set(pojo, value);
                    }
                } else if (List.class.isAssignableFrom(field.getType())) {
                    // Handle list fields
                    Type genericType = field.getGenericType();
                    if (genericType instanceof ParameterizedType parameterizedType) {

                        Class<?> listItemClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                        if (isPrimitiveOrWrapper(listItemClass) || listItemClass == String.class) {
                            // List of primitives or strings
                            List<Object> list = mapPrimitiveList(properties, propertyName, listItemClass);
                            field.set(pojo, list);
                        } else {
                            // List of POJOs
                            List<Object> list = mapListProperties(properties, propertyName, listItemClass);
                            field.set(pojo, list);
                        }
                    }
                } else {
                    // Handle nested objects
                    Properties nestedProperties = extractNestedProperties(properties, propertyName);
                    if (!nestedProperties.isEmpty()) {
                        Object nestedObject = mapPropertiesToPojo(nestedProperties, field.getType());
                        field.set(pojo, nestedObject);
                    }
                }
            }
            return pojo;
        } catch (IllegalArgumentException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to map properties to POJO", e);
        }
    }

    /**
     * Maps a list of properties with the same prefix into a list of POJOs.
     *
     * @param properties  the properties object
     * @param prefix      the prefix for the list items
     * @param listItemClass the class of the list items
     * @return a list of mapped POJOs
     */
    private static List<Object> mapListProperties(Properties properties, String prefix, Class<?> listItemClass) {
        List<Object> list = new ArrayList<>();
        int index = 0;

        while (true) {
            String indexedPrefix = prefix + "[" + index + "]";
            Properties nestedProperties = extractNestedProperties(properties, indexedPrefix);
            if (nestedProperties.isEmpty()) {
                break;
            }
            Object item = mapPropertiesToPojo(nestedProperties, listItemClass);
            list.add(item);
            index++;
        }
        return list;
    }

    /**
     * Maps a list of primitive properties with the same prefix.
     *
     * @param properties the properties object
     * @param prefix     the prefix for the list items
     * @param itemClass  the class of the list items
     * @return a list of mapped primitive values
     */
    private static List<Object> mapPrimitiveList(Properties properties, String prefix, Class<?> itemClass) {
        List<Object> list = new ArrayList<>();
        int index = 0;

        while (true) {
            String indexedKey = prefix + "[" + index + "]";
            String propertyValue = properties.getProperty(indexedKey);
            if (propertyValue == null) {
                break;
            }
            list.add(convertValue(propertyValue, itemClass));
            index++;
        }
        return list;
    }

    /**
     * Extracts nested properties for a specific prefix.
     *
     * @param properties the source properties
     * @param prefix     the prefix to match
     * @return a new Properties object containing only the matching nested properties
     */
    private static Properties extractNestedProperties(Properties properties, String prefix) {
        Properties nestedProperties = new Properties();
        String prefixWithDot = prefix + ".";
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefixWithDot)) {
                String nestedKey = key.substring(prefixWithDot.length());
                nestedProperties.setProperty(nestedKey, properties.getProperty(key));
            }
        }
        return nestedProperties;
    }

    /**
     * Checks if the class is a primitive or its wrapper.
     *
     * @param clazz the class to check
     * @return true if the class is a primitive or wrapper, false otherwise
     */
    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == Integer.class || clazz == Long.class
                || clazz == Double.class || clazz == Boolean.class || clazz == Character.class
                || clazz == Byte.class || clazz == Short.class || clazz == Float.class;
    }

    /**
     * Converts a string value to the specified type
     * @param value the string value
     * @param targetType the target type
     * @return the converted value
     */
    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        // Add other types as needed
        throw new IllegalArgumentException("Unsupported field type: " + targetType);
    }
}
