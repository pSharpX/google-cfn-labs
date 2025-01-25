package com.onebank.taskmaster.sendnotification.sms.provider.plivo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onebank.taskmaster.sendnotification.exception.BadRequestException;
import com.onebank.taskmaster.sendnotification.exception.InternalServerException;
import com.onebank.taskmaster.sendnotification.exception.ResourceNotFoundException;
import com.onebank.taskmaster.sendnotification.exception.utils.ExceptionConstantsUtils;
import com.onebank.taskmaster.sendnotification.sms.model.plivo.PlivoErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
public class PlivoClientErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        String errorMessage;
        try (InputStream bodyIs = response.body().asInputStream()) {
            PlivoErrorResponse errorResponse = this.objectMapper.readValue(bodyIs, PlivoErrorResponse.class);
            errorMessage = String.format("[%s] %s", errorResponse.getCode(), errorResponse.getMessage());
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
