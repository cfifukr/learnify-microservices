package com.example.course_service.service;

import com.example.course_service.dto.update.ModuleProgressUpdateDto;
import com.example.course_service.exceptions.ModuleNotFoundException;
import com.example.course_service.model.ModuleProgress;
import com.example.course_service.repository.ModuleProgressRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ModuleProgressService {
    private final ModuleProgressRepository moduleProgressRepository;

    public ModuleProgressService(ModuleProgressRepository moduleProgressRepository) {
        this.moduleProgressRepository = moduleProgressRepository;
    }


    public ModuleProgress updateModuleProgress(ModuleProgressUpdateDto dto,
                                               Long moduleProgressId){

        ModuleProgress moduleProgress = moduleProgressRepository.findById(moduleProgressId)
                .orElseThrow(() -> new ModuleNotFoundException("ModuleProgress with id: " + moduleProgressId  + "wasn`t found " ));


        ModuleProgress moduleProgressUpdated = dto.updateModuleProgress(moduleProgress);

        return moduleProgressRepository.save(moduleProgressUpdated);

    }

    public ModuleProgress getModuleProgressById(Long moduleProgressId) {
        ModuleProgress moduleProgress = moduleProgressRepository.findById(moduleProgressId)
                .orElseThrow(() -> new ModuleNotFoundException("ModuleProgress with id " + moduleProgressId + " not found"));
        return moduleProgress;
    }



}
