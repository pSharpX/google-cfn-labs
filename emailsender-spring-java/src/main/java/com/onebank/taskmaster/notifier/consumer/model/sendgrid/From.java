package com.onebank.taskmaster.notifier.consumer.model.sendgrid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class From {
    private String email;
    private String name;
}
