package com.onebank.taskmaster.searchtask.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagEntity {
    private Long id;
    private String name;
    private Set<TaskEntity> tasks = new HashSet<>();

    public TagEntity(String name) {
        this.name = name;
    }
}
