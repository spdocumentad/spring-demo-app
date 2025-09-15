package com.example.demo.employee.service;

import com.example.demo.core.exception.ResourceNotFoundException;
import com.example.demo.core.utils.TokenUtil;
import com.example.demo.employee.dao.repository.EmployeeRepository;
import com.example.demo.employee.dto.EmployeeRequest;
import com.example.demo.employee.dto.EmployeeResponse;
import com.example.demo.employee.mapper.EmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    private final TokenUtil tokenUtil;
    @Override
    public List<EmployeeResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public EmployeeResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("No category found with the provided id: %s", id)));
    }
    @Override
    @Transactional
    public String create(EmployeeRequest request) {
        var category = repository.save(
                mapper.toEntity(request)
        );
        return String.format("Employee created successfully with id: %s", category.getId());
    }

    @Override
    @Transactional
    public String update(Long id, EmployeeRequest request) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(request.name());
                    employee.setDepartment(request.department());
                    employee.setSalary(request.salary());
                    employee.setUserId(tokenUtil.getUserId());
                    mapper.toDto(repository.save(employee));
                    return String.format("Category updated successfully with id: %s", id);
                })
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Cannot update the category:: No category found with the provided id: %s", id)));

    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException(
                    String.format("Cannot delete the category:: No category found with the provided id: %s", id));

        repository.deleteById(id);
    }

}