package com.onebank.taskmaster.searchtask.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PropertiesPrefix(value = "task-master.datasource.control-plane")
public class FunctionConfigProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
