package com.example.employeeoffice.service.impl;

//import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
//import com.example.employeeoffice.dto.EmployeeDto;
//import com.example.employeeoffice.entity.Department;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.generator.PasswordGenerator;
//import com.example.employeeoffice.mapper.EmployeeMapper;
import com.example.employeeoffice.mapper.EmployeeMapper;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.repository.EmployeeRepository;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import com.example.employeeoffice.utils.PasswordHashing;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PersonalInfoRepository personalInfoRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public Employee getEmployeeById(UUID empId) {
        Employee employee = employeeRepository.getEmployeeByEmpId(empId);
        if (employee == null) {
            throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);

        }
        return employee;
    }

    @Override
    @Transactional
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
    @Transactional
    public EmployeeAfterRegistrationDto createEmployee(EmployeeRegistrationDto employeeRegistrationDto) {
        //PersonalInfo personalInfo = personalInfoRepository.findPersonalInfoByEmail(employeeRegistrationDto.getEmail());
        if (personalInfoRepository.existsByEmail(employeeRegistrationDto.getEmail())) {
            throw new EmployeeAlreadyExistException(ErrorMessage.EMPLOYEE_ALREADY_EXIST_EXCEPTION);
        }
        Role userRole = roleRepository.findByRoleName(RolesName.USER);
        if (userRole == null) {
            throw new RoleNotFoundException("ROLE_NOT_FOUND_EXCEPTION");
        }

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setBirthday(LocalDate.parse(employeeRegistrationDto.getBirthday()));
        personalInfo.setUsername(employeeRegistrationDto.getUsername());
        personalInfo.setPhoneNumber(employeeRegistrationDto.getPhoneNumber());
        personalInfo.setEmail(employeeRegistrationDto.getEmail());
        personalInfo.setPassword(employeeRegistrationDto.getPassword());
        personalInfo.setSalary(employeeRegistrationDto.getSalary());

        personalInfo.setRoles(Collections.singleton(userRole));
//        Role userRole = new Role();
//        userRole.setRoleName(RolesName.USER);
//        roleRepository.save(userRole);
//        personalInfo.setRoles(Collections.singleton(userRole));

        personalInfo = personalInfoRepository.save(personalInfo);

        Employee entity = employeeMapper.toEntity(employeeRegistrationDto);

        entity.setPersInfo(personalInfo);

        Department department = departmentRepository.findByDepName(DepartmentName.valueOf(employeeRegistrationDto.getDepartment()));
        if (department == null) {
            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);
        }

        entity.setDepartment(department);
       // entity.getPersInfo().getRoles().add(userRole);

        Employee savedEmployee = employeeRepository.save(entity);

        return employeeMapper.toDto(savedEmployee);
    }

}


