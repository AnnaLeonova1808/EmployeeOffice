package com.example.employeeoffice.repositiry;

import com.example.employeeoffice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee getEmployeeByEmpId(UUID empId);

//    UUID findAllById(String name);
//    //Employee createEmployee(Employee employee);
}
