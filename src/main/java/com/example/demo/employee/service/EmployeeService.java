package com.example.demo.employee.service;


import com.example.demo.core.common.BaseService;
import com.example.demo.employee.dto.EmployeeRequest;
import com.example.demo.employee.dto.EmployeeResponse;

public interface EmployeeService extends BaseService<Long, EmployeeResponse> {
    String create(EmployeeRequest request);

    String update(Long id, EmployeeRequest request);
}
