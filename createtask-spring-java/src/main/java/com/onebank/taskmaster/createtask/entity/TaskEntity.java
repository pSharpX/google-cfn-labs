package com.onebank.taskmaster.createtask.entity;

import com.onebank.taskmaster.createtask.model.TaskCreationStatus;
import com.onebank.taskmaster.createtask.model.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 200)
    @NotNull
    private String title;

    @Length(max = 800)
    @NotNull
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tasks_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();

    @NotNull
    private Integer weight = 0;

    @NotNull
    private TaskStatus progressStatus = TaskStatus.TODO;

    @NotNull
    private TaskCreationStatus status = TaskCreationStatus.PENDING;
}
