package com.onebank.taskmaster.createnotification.converter;

import lombok.NonNull;

public interface ConvertTo<I,O> {
    O convert(@NonNull I input);
}
