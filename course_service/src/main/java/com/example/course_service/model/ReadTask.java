package com.example.course_service.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
public class ReadTask extends Task{
    private String textTask;
}
