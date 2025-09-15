package com.example.demo.core.common;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface BaseMapper<E, R, D> {
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "userId", ignore = true)
    })
    E toEntity(R requestDto);
    D toDto(E entity);
}