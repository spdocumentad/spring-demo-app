package com.example.demo.employee.mapper;

import com.example.demo.core.common.BaseMapper;
import com.example.demo.employee.dao.entity.Employee;
import com.example.demo.employee.dto.EmployeeRequest;
import com.example.demo.employee.dto.EmployeeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeRequest, EmployeeResponse> {

    // Request -> Entity
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "userId", source = "userId")
    })
    Employee toEntity(EmployeeRequest dto, UUID userId);

    // Entity -> Response
    EmployeeResponse toDto(Employee category);

}
