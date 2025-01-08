package com.onebank.taskmaster.templatemanager.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.onebank.taskmaster.templatemanager.exception.BadRequestException;
import com.onebank.taskmaster.templatemanager.exception.ResourceNotFoundException;
import com.onebank.taskmaster.templatemanager.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.templatemanager.function.model.GenericMessage;
import com.onebank.taskmaster.templatemanager.function.model.Message;
import com.onebank.taskmaster.templatemanager.function.model.MessageBuilder;
import com.onebank.taskmaster.templatemanager.helper.FunctionUtils;
import lombok.RequiredArgsConstructor;

import java.net.HttpURLConnection;

@RequiredArgsConstructor
public class ManageTemplateFunction implements HttpFunction {
    private final FunctionExceptionHandler exceptionHandler;
    private final ObjectMapper objectMapper;

    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        response.appendHeader("Access-Control-Allow-Origin", "*");
        if ("OPTIONS".equals(request.getMethod())) {
            response.appendHeader("Access-Control-Allow-Methods", "GET");
            response.appendHeader("Access-Control-Allow-Headers", "Content-Type");
            response.appendHeader("Access-Control-Max-Age", "3600");
            response.setStatusCode(HttpURLConnection.HTTP_NO_CONTENT);
            return;
        }

        Message<String> message = new GenericMessage<>();
        response.appendHeader("Content-Type", "application/json");
        try {
            message = MessageBuilder
                    .withPayload("")
                    .setHeader(FunctionUtils.HTTP_STATUS_CODE, 202)
                    .build();
        } catch (IllegalArgumentException ex) {
            message = exceptionHandler.handleBadRequestException(ex);
        } catch (BadRequestException ex) {
            message = exceptionHandler.handleBadRequestException(ex);
        } catch (ResourceNotFoundException ex) {
            message = exceptionHandler.handleResourceNotFoundException(ex);
        } catch (Exception ex) {
            message = exceptionHandler.handleInternalServerException(ex);
        } finally {
            response.setStatusCode((int)message.getHeaders().get(FunctionUtils.HTTP_STATUS_CODE));
            response.getWriter().write(message.getPayload());
        }
    }
}
