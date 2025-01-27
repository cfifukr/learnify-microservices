package com.example.course_service.repository;

import com.example.course_service.model.ModuleProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, Long> {
}
