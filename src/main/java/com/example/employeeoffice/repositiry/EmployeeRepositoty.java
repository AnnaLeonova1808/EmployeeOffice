package com.example.employeeoffice.repositiry;

import com.example.employeeoffice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepositoty extends JpaRepository<Employee, UUID> {
    Employee getEmployeeByEmpId(UUID id);
}
