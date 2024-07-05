package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.enums.WorkScheduleName;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    Employee getEmployeeById(UUID empId);
    String deleteEmployeeById(UUID empId);
    EmployeeAfterRegistrationDto createEmployee(EmployeeRegistrationDto employeeRegistrationDto);

    List<Employee> getAllEmployeeByWorkSchedule(WorkScheduleName workSchedule);
    List<Employee> getAllEmployeeByWorkSchedule(String workScheduleName);

    List<Employee> showAllEmployees();


}

