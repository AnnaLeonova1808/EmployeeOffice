package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;

import java.util.Set;

public interface WorkScheduleService {
    WorkSchedule showByName(WorkScheduleName schedName);

    Set<String> showAllWorkSchedule();
}
