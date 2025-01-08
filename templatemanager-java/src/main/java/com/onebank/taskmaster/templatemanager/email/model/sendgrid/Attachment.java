package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

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
