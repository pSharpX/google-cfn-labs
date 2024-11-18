package com.onebank.taskmaster.searchtask.helper;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@UtilityClass
public class PropertiesMapper {
    public static <T> T mapPropertiesToPojo(Properties properties, Class<T> pojoClass) {
        try {
            // Convert Properties to a Map
            Map<String, Object> propertiesMap = properties.entrySet().stream()
                    .collect(
                            Collectors.toMap(
                                    e -> String.valueOf(e.getKey()),
                                    Map.Entry::getValue
                            )
                    );
            // Map the Map to the POJO
            return FunctionUtils.convertTo(pojoClass, propertiesMap);
        } catch (IllegalArgumentException | IOException e) {
            throw new RuntimeException("Failed to map properties to POJO", e);
        }
    }
}
