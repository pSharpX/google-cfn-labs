package com.onebank.taskmaster.templatemanager.email.model.mailchimp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetTemplateInfoRequest extends MailChimpBaseRequest {
    private String name;
}
