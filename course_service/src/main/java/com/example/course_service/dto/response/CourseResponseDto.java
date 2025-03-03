package com.example.course_service.dto.response;

import com.example.course_service.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String creatorId;
    private Integer timesBought;
    private Double rating;
    private Set<String> categories;

    private Set<CourseModuleResponseDto> modules;


    public static CourseResponseDto getDto(Course course){
        CourseResponseDto dto = new CourseResponseDto();
        dto.setId(course.getId());
        dto.setName(course.getName());
        dto.setCategories(course.getCategories());
        dto.setDescription(course.getDescription());
        dto.setCreatorId(course.getCreatorId());
        dto.setRating(course.getRating());
        dto.setTimesBought(course.getTimesBought());
        dto.setPrice(course.getPrice());

        if(course.getModules()!=null) {
            dto.setModules(course.getModules()
                    .stream().map(i -> CourseModuleResponseDto.getDto(i))
                    .collect(Collectors.toSet()));

        }
        return dto;
    }
}
