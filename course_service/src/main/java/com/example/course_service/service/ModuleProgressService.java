package com.example.course_service.service;

import com.example.course_service.repository.ModuleProgressRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleProgressService {
    private final ModuleProgressRepository moduleProgressRepository;

    public ModuleProgressService(ModuleProgressRepository moduleProgressRepository) {
        this.moduleProgressRepository = moduleProgressRepository;
    }


}
