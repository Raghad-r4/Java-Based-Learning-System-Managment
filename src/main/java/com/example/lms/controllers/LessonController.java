package com.example.lms.controllers;

import com.example.lms.models.LessonModel;
import com.example.lms.services.CourseService;
import com.example.lms.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/createLesson")
    public ResponseEntity<String> createLesson(@RequestBody LessonModel lessonModel) {

        courseService.addLessonToCourse(lessonModel.getCourseModel().getId(), lessonModel);
        lessonService.createLesson(lessonModel);
        return ResponseEntity.ok("Lesson created successfully");
    }

    @GetMapping("/displayLessons")
    public ResponseEntity<List<LessonModel>> displayLessons() {
        List<LessonModel> lessons = lessonService.displayLessons();
        return ResponseEntity.ok(lessons);
    }
    @PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
    @PostMapping("/generateOTP")
    public ResponseEntity<String> generateOTP(@RequestParam String otp, @RequestParam long lessonId) {
        return ResponseEntity.ok(lessonService.generateOTP(otp, lessonId));
    }
}
