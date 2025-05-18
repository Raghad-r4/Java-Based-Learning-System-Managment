package com.example.lms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;

@Entity
public class LessonModel {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Getter
    private String title;
    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "lesson_topics", joinColumns = @JoinColumn(name = "lesson_id"))
    @Column(name = "topic")
    private List<String> topics;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String teacherName;
    @Getter
    private LocalDateTime startDate;
    @Getter
    private int durationMinutes;
    @Setter
    private LocalDateTime endDate;
    @Getter
    @Setter
    private String OTP;
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "courseId")
    @JsonBackReference // Handles the back part of the reference
    private CourseModel courseModel;

    public LessonModel(String title, List<String> topics, String description, String teacherName, LocalDateTime startDate, int durationMinutes, String OTP) {
        this.title = title;
        this.topics = topics;
        this.description = description;
        this.teacherName = teacherName;
        this.startDate = startDate;
        this.durationMinutes = durationMinutes;
        this.endDate = startDate.plusMinutes(durationMinutes);
        this.OTP = OTP;
    }
    public LessonModel() {
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        updateEndDate();
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        updateEndDate();
    }
    public ChronoLocalDateTime getEndDate() {
        return endDate;
    }

    public void updateEndDate() {
        if (this.startDate != null) {
            this.endDate = this.startDate.plusMinutes(this.durationMinutes);
        }
    }
}
