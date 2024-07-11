package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowAllWorkSchedule;
import com.example.employeeoffice.annotation.ShowWorkScheduleByName;
import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.WorkScheduleNotFoundException;
import com.example.employeeoffice.service.interfaces.WorkScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
/**
 * REST controller for managing work schedules.
 * <p>
 * This controller provides endpoints for retrieving a work schedule by its name and retrieving all work schedules.
 * It uses {@link WorkScheduleService} for work schedule-related operations.
 * The controller is mapped to the "/work_schedules" URL path.
 */
@RestController
@RequestMapping("/work_schedules")
@RequiredArgsConstructor
public class WorkScheduleController {
    private final WorkScheduleService workScheduleService;

    /**
     * Retrieves a work schedule by its name.
     *
     * @param schedName The name of the work schedule.
     * @return The work schedule with the specified name.
     */
    @ShowWorkScheduleByName(path = "/showByName/{schedName}")
    public WorkSchedule showByName(@PathVariable (name = "schedName") String schedName) {
        try {
            return workScheduleService.showByName(WorkScheduleName.valueOf(schedName));

        } catch (IllegalArgumentException e) {

            throw new WorkScheduleNotFoundException(ErrorMessage.WORK_SCHEDULE_NOT_FOUND);
        }
    }

    /**
     * Retrieves all work schedules.
     *
     * @return A set of all work schedule names.
     */
    @ShowAllWorkSchedule(path = "/showAllWorkSchedule")
    public Set<String> showAllWorkSchedule() {

        return workScheduleService.showAllWorkSchedule();
    }
}
