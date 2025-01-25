package com.onebank.taskmaster.sendnotification.sms.model.twilio;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TwilioErrorResponse {
    private Integer code;
    private String message;
    private String moreInfo;
    private Integer status;
}
