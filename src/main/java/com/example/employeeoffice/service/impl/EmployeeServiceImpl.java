package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.mapper.EmployeeMapper;
import com.example.employeeoffice.repository.EmployeeRepository;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final EmployeeMapper employeeMapper;

    private final RoleRepository roleRepository;


    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public Employee getEmployeeById(UUID empId) {
        Employee employee = employeeRepository.getEmployeeByEmpId(empId);
        if (employee == null) {
            throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);
        }
        return employee;
    }

    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public String deleteEmployeeById(UUID empId) {

        Employee employee = employeeRepository.getEmployeeByEmpId(empId);

        if (employee != null) {
            employeeRepository.deleteById(empId);
            return "Employee with this ID was deleted SUCCESSFULLY";
        } else {
            throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);
        }
    }

    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public EmployeeAfterRegistrationDto createEmployee(EmployeeRegistrationDto employeeRegistrationDto) {

        if (personalInfoRepository.existsByEmail(employeeRegistrationDto.getEmail())) {
            throw new EmployeeAlreadyExistException(ErrorMessage.EMPLOYEE_ALREADY_EXIST_EXCEPTION);
        }

        Optional<Role> userRoleOptional = roleRepository.findByRoleName(RolesName.ROLE_USER);

        if (userRoleOptional.isEmpty()) {
            throw new RoleNotFoundException("ROLE_NOT_FOUND_EXCEPTION");
        }

        Role userRole = userRoleOptional.orElseThrow(() -> new RoleNotFoundException("ROLE_NOT_FOUND_EXCEPTION"));

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setBirthday(LocalDate.parse(employeeRegistrationDto.getBirthday()));
        personalInfo.setUsername(employeeRegistrationDto.getUsername());
        personalInfo.setPhoneNumber(employeeRegistrationDto.getPhoneNumber());
        personalInfo.setEmail(employeeRegistrationDto.getEmail());
        personalInfo.setPassword(employeeMapper.passwordEncoder.encode(employeeRegistrationDto.getPassword()));
        personalInfo.setSalary(employeeRegistrationDto.getSalary());

        personalInfo.setRoles(Collections.singleton(userRole));

        personalInfo = personalInfoRepository.save(personalInfo);

        Employee entity = employeeMapper.toEntity(employeeRegistrationDto);

        entity.setPersInfo(personalInfo);


        Employee savedEmployee = employeeRepository.save(entity);

        return employeeMapper.toDto(savedEmployee);
    }



    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public List<Employee> getAllEmployeeByWorkSchedule(WorkScheduleName workScheduleName) {
        List<Employee> employeeList = employeeRepository.findAllByWorkSchedule_SchedName(workScheduleName);
        if (employeeList.isEmpty()) {
            throw new ListOfEmployeeIsEmptyException(ErrorMessage.LIST_OF_EMPLOYEE_IS_EMPTY);
        }
        return employeeList;
    }

    @Override
    public List<Employee> getAllEmployeeByWorkSchedule(String workScheduleName) {
        try {
            WorkScheduleName scheduleName = WorkScheduleName.valueOf(workScheduleName.toUpperCase());
            return getAllEmployeeByWorkSchedule(scheduleName);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid work schedule");
        }

    }

    @Override
    public List<Employee> showAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();
        if (employeeList.isEmpty()) throw new ListOfEmployeeIsEmptyException(ErrorMessage.LIST_OF_EMPLOYEE_IS_EMPTY);
        return employeeList;
    }

}


