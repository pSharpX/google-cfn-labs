package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.onebank.taskmaster.notifier.consumer.model.To;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Personalization {
    private From from;
    private List<To> to;
    private List<To> cc;
    private List<To> bcc;
    private String subject;
    private Map<String, String> headers;
    private Map<String, String> substitutions;
    private Map<String, String> dynamicTemplateData;
    private Map<String, String> customArgs;
    private Long sendAt;
}
