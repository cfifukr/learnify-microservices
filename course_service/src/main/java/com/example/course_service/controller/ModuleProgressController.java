package com.example.course_service.controller;

import com.example.course_service.dto.response.ModuleProgressResponseDto;
import com.example.course_service.dto.update.ModuleProgressUpdateDto;
import com.example.course_service.model.ModuleProgress;
import com.example.course_service.service.ModuleProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/progress/module")
public class ModuleProgressController {
    private final ModuleProgressService moduleProgressService;

    public ModuleProgressController(ModuleProgressService moduleProgressService) {
        this.moduleProgressService = moduleProgressService;
    }

    @GetMapping("/{moduleId}")
    private ResponseEntity<?> getModuleById(@PathVariable Long moduleId) {

        ModuleProgress moduleProgress = moduleProgressService.getModuleProgressById(moduleId);

        return ResponseEntity.ok(ModuleProgressResponseDto.getDto(moduleProgress));

    };

    @PutMapping("/{moduleId}")
    private ResponseEntity<?> updateModule(@RequestBody ModuleProgressUpdateDto dto,
                                           @PathVariable Long moduleId) {

        ModuleProgress moduleProgress = moduleProgressService.updateModuleProgress(dto, moduleId);

        return ResponseEntity.ok(ModuleProgressResponseDto.getDto(moduleProgress));

    };




}
