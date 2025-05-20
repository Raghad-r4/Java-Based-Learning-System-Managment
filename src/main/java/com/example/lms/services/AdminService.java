package com.example.lms.services;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public String getAdminDashboard() {
        return "Admin Dashboard Content";
    }
}