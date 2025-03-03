package com.example.course_service.dto.update;

import com.example.course_service.exceptions.TaskNotFoundException;
import com.example.course_service.model.CourseProgress;
import com.example.course_service.model.ModuleProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleProgressUpdateDto {

    private Map<Long, Boolean> tasksStatus = new HashMap<>();
    private Boolean status;



    public ModuleProgress updateModuleProgress(ModuleProgress moduleProgress) {

        if(this.status != null){
            moduleProgress.setModuleStatus(this.status);
        }

        this.tasksStatus.keySet().forEach(key -> {
            if(!this.tasksStatus.containsKey(key)){
                throw new TaskNotFoundException("Task with id " + key + " not found");
            }
            moduleProgress.getTasksStatus().put(key, this.tasksStatus.get(key));
        });

        return moduleProgress;
    }
}
