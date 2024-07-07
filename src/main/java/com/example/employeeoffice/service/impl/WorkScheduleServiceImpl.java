package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.repository.WorkScheduleRepository;
import com.example.employeeoffice.service.interfaces.WorkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;

    @Override
    public WorkSchedule showByName(WorkScheduleName schedName) {
        WorkSchedule workSchedule = workScheduleRepository.findBySchedName(schedName);
        if (workSchedule == null) throw new WorkScheduleNotFoundException(schedName + "WorkSchedule not found");
        return workSchedule;
    }

    @Override
    public Set<String> showAllWorkSchedule() {
        List<WorkSchedule> workScheduleList = workScheduleRepository.findAll();
        if (workScheduleList.isEmpty()) throw new ListOfWorkScheduleIsEmptyException(ErrorMessage.LIST_OF_WORK_SCHEDULE_IS_EMPTY);
        return workScheduleList.stream()
                .map(WorkSchedule::getSchedName)
                .map(WorkScheduleName::name)
                .collect(Collectors.toSet());
    }
}
