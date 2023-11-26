package io.nangrande.todolist.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "task")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private LocalDateTime createdDate;

    @Getter
    @Setter
    private LocalDateTime modifiedDate;
    public TodoItem() {
    }

    LocalDateTime moscowTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));

    public TodoItem(String description) {
        this.description = description;
        this.complete = false;
        this.createdDate = LocalDateTime.now(ZoneId.from(moscowTime));
        this.modifiedDate = LocalDateTime.now(ZoneId.from(moscowTime));
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", complete=" + complete +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
