package com.example.course_service.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("text")
public class ReadTask extends Task{
    private String textTask;
}
