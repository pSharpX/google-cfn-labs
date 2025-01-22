package com.onebank.taskmaster.createnotification.notifier.serializer;

import com.google.inject.Inject;
import com.onebank.taskmaster.createnotification.helper.FunctionUtils;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serializer;

@RequiredArgsConstructor(onConstructor = @__({@Inject}))
public class JsonSerializer<T> implements Serializer<T> {

    @Override
    public byte[] serialize(String topic, T data) {
        return FunctionUtils.writeValueAsBytes(data);
    }
}
