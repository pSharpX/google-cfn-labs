package com.onebank.taskmaster.createtask.entity;

import com.onebank.taskmaster.createtask.entity.listeners.TaskCreationListener;
import com.onebank.taskmaster.createtask.model.TaskCreationStatus;
import com.onebank.taskmaster.createtask.model.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tasks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class, TaskCreationListener.class})
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
    @Builder.Default
    private Set<TagEntity> tags = new HashSet<>();

    @NotNull
    @Builder.Default
    private Integer weight = 0;

    @NotNull
    @Builder.Default
    private TaskStatus progressStatus = TaskStatus.TODO;

    @NotNull
    @Builder.Default
    private TaskCreationStatus status = TaskCreationStatus.PENDING;

    @Column
    private LocalDate dueDate;

    @Column(insertable = false)
    private LocalDateTime completionDate;

    @Column(columnDefinition = "boolean default true", insertable = false)
    @Builder.Default
    private Boolean enabled = true;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private LocalDateTime updateDatetime;
}
