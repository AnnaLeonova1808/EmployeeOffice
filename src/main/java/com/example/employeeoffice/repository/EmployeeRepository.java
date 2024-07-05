package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.AddressType;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Employee getEmployeeByEmpId(UUID empId);
    //List<Employee> getEmployeeByCity( String city);

//    @Query("SELECT e FROM Employee e JOIN e.persInfo p WHERE p.username = :username")
//    Employee findByUsername(@Param("username") String username);

//    @Query("SELECT e FROM Employee e JOIN e.persInfo p JOIN p.addresses a WHERE a.city = :city")
//    List<Employee> findAllByCity(@Param("city") String city);
    List<Employee> findAllByWorkSchedule_SchedName(WorkScheduleName schedName);

}
