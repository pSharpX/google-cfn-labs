package com.onebank.taskmaster.createtask.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "control-plane")
@Validated
public class ControlPlaneConfigProperties {
    private Database database;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Database {
        private Boolean enabled = false;
    }

}
