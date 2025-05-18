package com.example.lms.services;

import com.example.lms.models.AttendanceModel;
import com.example.lms.models.CourseModel;
import com.example.lms.models.LessonModel;
import com.example.lms.models.StudentModel;
import com.example.lms.repositories.AttendanceRepository;
import com.example.lms.repositories.LessonRepository;
import com.example.lms.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    private static final String LESSON_NOT_FOUND_MSG = "Lesson not found";

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository,
                             LessonRepository lessonRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    public List<AttendanceModel> displayAllAttendance() {
        return attendanceRepository.findAll();
    }

    public List<AttendanceModel> displayLessonAttendance(long lessonId) {
        LessonModel lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException(LESSON_NOT_FOUND_MSG));
        return attendanceRepository.findByLesson(lesson);
    }

    @Transactional
    public String attendLesson(int studentId, long lessonId, String otp) {
        // 1. Validate student exists
        StudentModel student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // 2. Validate lesson exists
        LessonModel lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

        // 3. Check if lesson is ongoing (using both startDate and calculated endDate)
        LocalDateTime currentTime = LocalDateTime.now();
        boolean isLessonOngoing = currentTime.isAfter(lesson.getStartDate()) &&
                currentTime.isBefore(lesson.getEndDate());

        if (!isLessonOngoing) {
            return "Lesson is not ongoing.";
        }

        // 4. Verify OTP (case-sensitive check)
        if (!lesson.getOTP().equals(otp)) {
            return "OTP is not correct.";
        }

        // 5. Check student enrollment in the course
        CourseModel course = lesson.getCourseModel();
        if (course == null) {
            return "Lesson is not associated with any course.";
        }

        if (!student.getCourses().contains(course)) {
            return "You are not enrolled in this course!";
        }

        // 6. Check for existing attendance record
        boolean alreadyAttended = attendanceRepository.existsByLessonAndStudent(lesson, student);
        if (alreadyAttended) {
            return "Attendance already recorded for this lesson.";
        }

        // 7. Create and save attendance record
        AttendanceModel attendance = new AttendanceModel();
        attendance.setLesson(lesson);
        attendance.setStudent(student);
        attendance.setAttended(true);
        attendance.setTimestamp(currentTime);

        attendanceRepository.save(attendance);

        return "You are successfully attend.";
    }
}