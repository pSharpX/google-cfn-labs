package com.onebank.taskmaster.templatemanager.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import okhttp3.OkHttpClient;
import org.zalando.logbook.okhttp.GzipInterceptor;

public class OkHttpClientConfigModule extends AbstractModule {

    @Provides
    @Singleton
    // TODO: Fix logbook integration (with Function F. invoker)
    public OkHttpClient buildOkHttpClient(
            //Logbook logbook
    ) {
        return new OkHttpClient.Builder()
                //.addNetworkInterceptor(new LogbookInterceptor(logbook))
                .addNetworkInterceptor(new GzipInterceptor())
                .build();
    }
}
