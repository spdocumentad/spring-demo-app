package com.example.demo.core.contract;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;


@MappedSuperclass
@Getter
@Setter
@SuperBuilder // ✅ required to allow builder inheritance
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "created_at", nullable = true, columnDefinition = "DATETIME2", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true, columnDefinition = "DATETIME2")
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
