package com.onebank.taskmaster.searchtask.service;

import java.util.List;

public interface GetConfigProperties {
    String get(String propertyName);
    List<String> retrieveAll(String prefixPropertyName);
}
