package com.onebank.taskmaster.templatemanager.email.provider.sendgrid;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.templatemanager.email.model.sendgrid.SendGridErrorResponse;
import com.onebank.taskmaster.templatemanager.exception.BadRequestException;
import com.onebank.taskmaster.templatemanager.exception.InternalServerException;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.exception.utils.ExceptionConstantsUtils;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class SendGridClientErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage;
        try (InputStream bodyIs = response.body().asInputStream()) {
            SendGridErrorResponse errorResponse = this.objectMapper.readValue(bodyIs, SendGridErrorResponse.class);
            errorMessage = String.format("[%s] %s", response.status(), errorResponse.getError());
        } catch (IOException e) {
            return new InternalServerException(ExceptionConstantsUtils.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        switch (response.status()) {
            case 400 -> throw new BadRequestException(ExceptionConstantsUtils.BAD_REQUEST, errorMessage);
            case 404 -> throw new ResourceNotFoundException(ExceptionConstantsUtils.NOT_FOUND, errorMessage);
            case 503, 500 -> throw new InternalServerException(ExceptionConstantsUtils.INTERNAL_SERVER_ERROR, errorMessage);
            default -> {
                return (new Default()).decode(methodKey, response);
            }
        }
    }
}
