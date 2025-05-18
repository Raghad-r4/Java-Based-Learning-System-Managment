package com.example.lms.services;

import com.example.lms.dtos.CourseDTO;
import com.example.lms.models.CourseModel;
import com.example.lms.models.LessonModel;
import com.example.lms.models.StudentModel;
import com.example.lms.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(CourseModel course) {
        courseRepository.save(course);
    }

    public List<CourseDTO> displayCourses() {
        List<CourseModel> courses = courseRepository.findAll();
        return courses.stream()
                .map(c -> {
                    CourseDTO x = new CourseDTO(c);
                    x.setStudents(c.getStudents());
                    return x;
                })
                .toList();
    }

    public void addLessonToCourse(Long courseId, LessonModel lesson) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            lesson.setCourseModel(course);
            course.addLesson(lesson);
            courseRepository.save(course);
        }
    }

    public void addMediaFile(String courseId, String filePath) {
        CourseModel course = courseRepository.findByCourseId(courseId);
        if (course != null) {
            course.getMediaFiles().add(filePath);
            courseRepository.save(course);
        }
    }

    public void deleteStudentFromCourse(Long courseId, Integer studentId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.getStudents().removeIf(student -> Objects.equals(student.getId(), studentId));
            courseRepository.save(course);
        }
    }

    // New method to delete all enrolled students
    public void deleteAllStudentsFromCourse(Long courseId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            course.getStudents().clear();
            courseRepository.save(course);
        }
    }

    public void updateCourseDetails(Long courseId, CourseModel updatedCourse) {
        CourseModel existingCourse = courseRepository.findById(courseId).orElse(null);
        if (existingCourse != null) {
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setDurationHours(updatedCourse.getDurationHours());
            courseRepository.save(existingCourse);
        }
    }

    public List<StudentModel> getStudentsByCourseId(Long courseId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getStudents(); // Return the list of students enrolled in the course
        }
        return new ArrayList<>(); // Return an empty list if the course is not found
    }

    // Access media files
    public List<String> getMediaFilesByCourseId(Long courseId) {
        CourseModel course = courseRepository.findById(courseId).orElse(null);
        if (course != null) {
            return course.getMediaFiles(); // Return the list of media files
        }
        return new ArrayList<>(); // Return an empty list if the course is not found
    }
}
