package com.onebank.taskmaster.sendnotification.sms.model.twilio;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import feign.form.FormProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SendMessageParam {
    @FormProperty("To")
    private String to;
    @FormProperty("StatusCallback")
    private String statusCallback;
    @FormProperty("ApplicationSid")
    private String applicationSid;
    @FormProperty("ProvideFeedback")
    private Boolean provideFeedback;
    @FormProperty("Attempt")
    private Integer attempt;
    @FormProperty("ValidityPeriod")
    private Integer validityPeriod;
    @FormProperty("ForceDelivery")
    private Boolean forceDelivery;
    @FormProperty("ContentRetention")
    private String contentRetention;
    @FormProperty("AddressRetention")
    private String addressRetention;
    @FormProperty("SmartEncoded")
    private Boolean smartEncoded;
    @FormProperty("ShortenUrls")
    private Boolean shortenUrls;
    @FormProperty("ScheduleType")
    private String scheduleType;
    @FormProperty("SendAt")
    private String sendAt;
    @FormProperty("SendAsMms")
    private Boolean sendAsMms;
    @FormProperty("ContentVariables")
    private String contentVariables;
    @FormProperty("RiskCheck")
    private String riskCheck;
    @FormProperty("From")
    private String from;
    @FormProperty("MessagingServiceSid")
    private String messagingServiceSid;
    @FormProperty("Body")
    private String body;
    @FormProperty("MediaUrl")
    private List<String> mediaUrl;
    @FormProperty("ContentSid")
    private String contentSid;
}
