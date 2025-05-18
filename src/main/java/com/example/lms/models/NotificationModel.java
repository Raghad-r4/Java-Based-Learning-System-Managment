package com.example.lms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;


@Entity
public class NotificationModel {

    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationID;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Setter
    @Getter
    private String type;

    @Setter
    @org.jetbrains.annotations.NotNull
    private String message;

    @Getter
    private LocalDateTime timestamp;

    @Setter
    @Getter
    private Boolean isRead;

    public NotificationModel() {}

    public NotificationModel(User user, String type, @NotNull String message, LocalDateTime timestamp) {
        this.user = user;
        this.type = type;
        this.message = message;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now();
        this.isRead = false;
    }

    public User getStudent() {
        return user;
    }

    public void setStudent(StudentModel student) {
        this.user = student;
    }

    public @NotNull String getMessage() {
        return message;
    }

}
