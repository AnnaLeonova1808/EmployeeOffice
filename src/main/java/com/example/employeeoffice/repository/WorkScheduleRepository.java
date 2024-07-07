package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, UUID> {
    WorkSchedule findBySchedName(WorkScheduleName schedName);
    WorkSchedule saveAndFlush(WorkSchedule workSchedule);
}
