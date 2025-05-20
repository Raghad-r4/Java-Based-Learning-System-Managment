package com.example.lms.controllers;

import com.example.lms.models.AuthentictationResponse;
import com.example.lms.models.Profile;
import com.example.lms.models.User;
import com.example.lms.services.AdminService;
import com.example.lms.services.InstructorService;
import com.example.lms.services.StudentService;
import com.example.lms.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private InstructorService instructorService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthentictationResponse> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user)); // Register method in service
    }

    @PostMapping("/login")
    public ResponseEntity<AuthentictationResponse> login(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user)); // Login method in service
    }

    @PostMapping("/editprofile")
    public ResponseEntity<Object> editProfile(@RequestBody Profile userProfile){
        return ResponseEntity.ok(userService.editProfile(userProfile));
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<Object> viewProfile(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.viewProfile(userId));
    }
    @GetMapping
    public ResponseEntity<?> getDashboard(Authentication auth) {
        User user = userService.getUserByUsername(auth.getName());

        switch (user.getRole().toUpperCase()) {
            case "ADMIN":
                return ResponseEntity.ok(adminService.getAdminDashboard());
            case "STUDENT":
                return ResponseEntity.ok(studentService.getAllStudents());
            case "INSTRUCTOR":
                return ResponseEntity.ok(instructorService.getInstructorDashboard());
            default:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        }
    }
}