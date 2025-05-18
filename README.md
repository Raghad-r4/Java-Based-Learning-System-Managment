# Java-Based-Learning-Management-System

## Project Overview
The LMS is a web-based application designed to manage and organize online courses and assessments from both student and instructor perspectives. It supports user registration, course management, assessments, grading, performance tracking, and notifications to ensure a smooth educational experience.

## Key Features

### 1. User Management
- **User Types**: Admin, Instructor, Student.
  - **Admin**: Manages system settings, user creation, and course management.
  - **Instructor**: Creates and manages course content, assignments, quizzes, grades, and attendance.
  - **Student**: Enrolls in courses, views content, submits assignments, and checks grades.
- **Core Functionalities**:
  - **User Registration and Login** (role-based access)
  - **Profile Management**: Users can view and update their profile information.

### 2. Course Management
- **Course Creation**:
  - Instructors can create courses with details like title, description, and duration.
  - Media files (videos, PDFs, audio) can be uploaded to enrich the course content.
  - Each course consists of multiple lessons.
- **Enrollment Management**:
  - Students can browse and enroll in courses.
  - Admins and instructors can view enrolled students for each course.
- **Attendance Management**:
  - Instructors can generate an OTP per lesson to verify student attendance.
  - Students can select a lesson and enter the OTP to mark attendance.

### 3. Assessment & Grading
- **Assessment Types**: Quizzes and Assignments.
- **Quiz Creation**:
  - Instructors can create quizzes with various question types (MCQ, True/False, Short Answers).
  - Supports a question bank per course with randomized selection.
- **Assignment Submission**:
  - Students can upload assignments for review.
- **Grading and Feedback**:
  - Instructors grade assignments.
  - Students receive automated quiz feedback and manual feedback on assignments.

### 4. Performance Tracking
- Instructors can monitor students' quiz scores, assignment submissions, and attendance.

### 5. Notifications
- **System Notifications**:
  - Students and instructors can view notifications for enrollment confirmations, grades, and course updates.
  - Unread and read notifications can be viewed separately.
- **Email Notifications** (Bonus):
  - Students receive email notifications for enrollments, graded assignments, and course updates.

### 6. Bonus Features
- **Role-Based Access Control**:
  - Uses Spring Security for authentication and authorization.
  - Access is restricted based on user role (Admin, Instructor, Student).
- **Performance Analytics**:
  - Admins and instructors can generate Excel reports on student performance.
  - Visualizations (charts) of progress, performance, and course completion.
  

