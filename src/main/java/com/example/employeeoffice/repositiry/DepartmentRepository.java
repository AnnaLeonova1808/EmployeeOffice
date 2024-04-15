package com.example.employeeoffice.repositiry;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository  <Department, UUID> {
    Department findByDepName (DepartmentName depName);
}
