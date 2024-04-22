package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee getEmployeeByEmpId(UUID empId);
}
