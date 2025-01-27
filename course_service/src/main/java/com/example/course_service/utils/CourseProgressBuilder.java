package com.example.course_service.utils;

import com.example.course_service.model.Course;
import com.example.course_service.model.CourseModule;
import com.example.course_service.model.CourseProgress;
import com.example.course_service.model.ModuleProgress;

public class CourseProgressBuilder {



    public static CourseProgress buildCourseProgress(Course course, String keycloakId) {

        CourseProgress courseProgress = new CourseProgress();
        courseProgress.setKeycloakId(keycloakId);
        courseProgress.setCourseId(course.getId());
        courseProgress.setStatus(false);

        return courseProgress;


    }

    public static ModuleProgress buildModuleProgress(CourseModule module, String keycloakId,
                                CourseProgress courseProgress) {
        ModuleProgress moduleProgress = new ModuleProgress();
        moduleProgress.setKeycloakId(keycloakId);
        moduleProgress.setModuleId(module.getId());
        moduleProgress.setModuleStatus(false);
        moduleProgress.setCourseProgress(courseProgress);

        module.getTasks().forEach(task -> {
            moduleProgress.addOrChangeTaskStatus(task.getId(), false);
        });

        return moduleProgress;
    }
}
