package com.example.statistic_service.repositories;

import com.example.statistic_service.model.TeacherStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherStatRepository extends JpaRepository<TeacherStat, Long> {
}
