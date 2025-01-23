package com.onebank.taskmaster.sendnotification.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.okhttp.GzipInterceptor;
import org.zalando.logbook.okhttp.LogbookInterceptor;

@RequiredArgsConstructor
public class OkHttpClientConfigModule extends AbstractModule {

    @Provides
    @Singleton
    public OkHttpClient buildOkHttpClient(final Logbook logbook) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new LogbookInterceptor(logbook))
                .addNetworkInterceptor(new GzipInterceptor())
                .build();
    }
}
