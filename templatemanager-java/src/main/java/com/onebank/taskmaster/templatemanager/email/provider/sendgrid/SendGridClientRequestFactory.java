package com.onebank.taskmaster.templatemanager.email.provider.sendgrid;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class SendGridClientRequestFactory {
    @Named("sendgridProperties")
    private final Properties config;

}
