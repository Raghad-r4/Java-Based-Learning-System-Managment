package com.example.lms.dtos;

import com.example.lms.models.CourseModel;
import com.example.lms.models.StudentModel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StudentDTO {
    @Setter
    int id;
    @Setter
    String name;
    @Setter
    String email;
    public List<CourseDTO> courses;
    public StudentDTO(StudentModel student) {
        this.id = student.getId();
        this.name = student.getName();
        this.email = student.getEmail();
        this.courses = new ArrayList<>();
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses.stream().map(CourseDTO::new).collect(Collectors.toList());
    }
}
