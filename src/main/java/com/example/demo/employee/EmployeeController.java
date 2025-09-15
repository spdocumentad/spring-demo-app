package com.example.demo.employee;

import com.example.demo.employee.dto.EmployeeRequest;
import com.example.demo.employee.dto.EmployeeResponse;
import com.example.demo.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
