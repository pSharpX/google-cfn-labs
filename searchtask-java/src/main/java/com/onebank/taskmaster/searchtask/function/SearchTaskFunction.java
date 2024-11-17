package com.onebank.taskmaster.searchtask.function;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.onebank.taskmaster.searchtask.exception.BadRequestException;
import com.onebank.taskmaster.searchtask.exception.ResourceNotFoundException;
import com.onebank.taskmaster.searchtask.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.searchtask.function.interceptors.Auditable;
import com.onebank.taskmaster.searchtask.function.model.GenericMessage;
import com.onebank.taskmaster.searchtask.function.model.Message;
import com.onebank.taskmaster.searchtask.function.model.MessageBuilder;
import com.onebank.taskmaster.searchtask.helper.FunctionUtils;
import com.onebank.taskmaster.searchtask.helper.QueryStringToPojo;
import com.onebank.taskmaster.searchtask.model.SearchTaskParam;
import com.onebank.taskmaster.searchtask.model.SearchTaskResponse;
import com.onebank.taskmaster.searchtask.service.SearchTask;
import lombok.RequiredArgsConstructor;

import java.net.HttpURLConnection;

@Auditable
@RequiredArgsConstructor
public class SearchTaskFunction implements HttpFunction {

    private final SearchTask searchTask;
    private final FunctionExceptionHandler exceptionHandler;

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
            SearchTaskParam params = QueryStringToPojo.convertTo(request.getQuery().orElse(""), SearchTaskParam.class);
            SearchTaskResponse searchTaskResponse = searchTask.search(params);
            message = MessageBuilder
                        .withPayload(FunctionUtils.toJson(searchTaskResponse))
                        .setHeader(FunctionUtils.HTTP_STATUS_CODE, 200)
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
