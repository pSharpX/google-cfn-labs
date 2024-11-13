package com.onebank.taskmaster.searchtask.function;

import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;

public class SearchTaskFunction implements HttpFunction {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws Exception {
        response.getWriter().write("Hello, World");
    }
}
