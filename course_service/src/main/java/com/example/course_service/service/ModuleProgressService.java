package com.example.course_service.service;

import com.example.course_service.dto.update.ModuleProgressUpdateDto;
import com.example.course_service.exceptions.ModuleNotFoundException;
import com.example.course_service.exceptions.TaskNotFoundException;
import com.example.course_service.model.ModuleProgress;
import com.example.course_service.repository.ModuleProgressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ModuleProgressService {
    private final ModuleProgressRepository moduleProgressRepository;

    public ModuleProgressService(ModuleProgressRepository moduleProgressRepository) {
        this.moduleProgressRepository = moduleProgressRepository;
    }


    public ModuleProgress getModuleProgressById(Long moduleProgressId) {
        ModuleProgress moduleProgress = moduleProgressRepository.findById(moduleProgressId)
                .orElseThrow(() -> new ModuleNotFoundException("ModuleProgress with id " + moduleProgressId + " not found"));
        return moduleProgress;
    }



    public ModuleProgress updateModuleProgress(ModuleProgressUpdateDto dto,
                                               Long moduleProgressId){

        ModuleProgress moduleProgress = moduleProgressRepository.findById(moduleProgressId)
                .orElseThrow(() -> new ModuleNotFoundException("ModuleProgress with id: " + moduleProgressId  + "wasn`t found " ));

        ModuleProgress moduleProgressUpdated = dto.updateModuleProgress(moduleProgress);

        if(checkAllTasksIdDone(dto.getTasksStatus())){
            // when all tasks are done, i delete map to save memory
            moduleProgressUpdated.setModuleStatus(true);
            moduleProgressUpdated.setTasksStatus(null);
            return moduleProgressRepository.save(moduleProgressUpdated);
        }

        return moduleProgressRepository.save(moduleProgressUpdated);

    }




    private boolean checkAllTasksIdDone(Map<Long, Boolean> map) {
        return map.values().stream().allMatch(b->b);

    }



}
