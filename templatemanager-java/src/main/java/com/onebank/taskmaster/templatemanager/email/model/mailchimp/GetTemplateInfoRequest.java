package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTemplateInfoRequest extends MailChimpBaseRequest {
    private String name;
}
