package com.onebank.taskmaster.sendnotification.sms.model.twilio;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SendMessageResponse {
    private String accountSid;
    private String apiVersion;
    private String body;
    private String dateCreated;
    private String dateSent;
    private String dateUpdated;
    private String direction;
    private String errorCode;
    private String errorMessage;
    private String from;
    private String messagingServiceSid;
    private String numMedia;
    private String numSegments;
    private String price;
    private String priceUnit;
    private String sid;
    private String status;
    private ResourceUri subresourceUris;
    private String to;
    private String uri;
}
