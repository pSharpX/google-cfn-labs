package com.onebank.taskmaster.createtask.converter;

import lombok.NonNull;

public interface ConvertTo<I,O> {
    O convert(@NonNull I input);
}
