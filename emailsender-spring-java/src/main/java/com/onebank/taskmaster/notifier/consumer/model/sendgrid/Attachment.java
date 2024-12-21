package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
    private String type;
    private String content;
    private String filename;
    private String disposition;
    private String contentId;
}
