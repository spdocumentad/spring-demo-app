package com.example.demo.employee.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeResponse(
        Long id,
        String name,
        String department,
        Double salary,
        UUID userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}