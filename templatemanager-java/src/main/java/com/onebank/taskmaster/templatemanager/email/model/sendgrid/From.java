package com.onebank.taskmaster.templatemanager.email.model.sendgrid;

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
