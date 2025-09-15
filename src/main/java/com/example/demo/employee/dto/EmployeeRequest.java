package com.example.demo.employee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeRequest(
        @NotBlank(message = "Name is required") String name,
        String department,
        @NotNull(message = "Salary is required") Double salary
) {}