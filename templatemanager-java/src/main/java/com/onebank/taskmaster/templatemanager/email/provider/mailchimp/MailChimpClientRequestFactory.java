package com.onebank.taskmaster.templatemanager.email.provider.mailchimp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class MailChimpClientRequestFactory {
    @Named("mailChimpProperties")
    private final Properties config;

}
