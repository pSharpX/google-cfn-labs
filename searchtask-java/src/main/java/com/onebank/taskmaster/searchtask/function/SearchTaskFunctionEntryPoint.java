package com.onebank.taskmaster.searchtask.function;

import com.onebank.taskmaster.searchtask.config.InjectorProvider;
import com.onebank.taskmaster.searchtask.function.exception.FunctionExceptionHandler;
import com.onebank.taskmaster.searchtask.service.SearchTask;

public class SearchTaskFunctionEntryPoint extends SearchTaskFunction {
    public SearchTaskFunctionEntryPoint() {
        super(InjectorProvider.getInjector().getInstance(SearchTask.class), new FunctionExceptionHandler());
    }
}
