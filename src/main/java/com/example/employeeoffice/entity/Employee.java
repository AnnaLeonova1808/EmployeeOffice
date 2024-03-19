package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.EmployeeGrade;
import com.example.employeeoffice.entity.enums.StatusEmployee;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.entity.enums.WorkPlaceLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "employee_id", columnDefinition = "BINARY(16)")
    private UUID empId;
    @Column(name = "first_name")
    private String empFirstName;
    @Column(name = "last_lame")
    private String empLastName;
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_grade")
    private EmployeeGrade empGrade;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department; // ! связь с департаментом, 1) для определения руководителя(11) и 2) для определения списка кто входит в департамент из сотрудников(4)
    @Column(name = "hire_date")
    private LocalDate hireDate;
    @Column(name = "termination_date")
    private LocalDate termDate;
    @ManyToOne
    @JoinColumn(name = "department_manager_id")
    private Employee depManager; // (11)Руководитель сотрудника (или руководитель департамента)
    @Enumerated(EnumType.STRING)
    @Column(name = "workplace_location")
    private WorkPlaceLocation workPlaceLocation;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_employee")
    private StatusEmployee statusEmployee;

    @Column(name = "vacation_plan")
    private String vacPlan;
    @Column(name = "work_schedule")
    @Enumerated(EnumType.STRING)
    private WorkScheduleName workSchedule;
    @OneToOne
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo; //(2) у каждого сотрудника может быть только одна личная информация
    @Column(name = "created_at")
    private Timestamp createdAt;
    @OneToMany(mappedBy = "employee")
    private Set<Address> addresses; // (8) Один сотрудник может иметь несколько адресов (например, домашний и рабочий адрес)

    @OneToMany(mappedBy = "employee_id")
    private Set<Vacation> vacations; // (7) один сотрудник может иметь несколько записей об отпусках.

    @OneToMany(mappedBy = "substitution_emp_id")
    private Set<Vacation> substitutedVacations; //(10) связь между сотрудником, который замещает другого во время отпуска и отпуском
    @ManyToMany
    @JoinTable(
            name = "events_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Events> events;  // (9)  На разные события (корпоратив, конференция) приглашают несколько сотрудников


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(empFirstName, employee.empFirstName) && Objects.equals(empLastName, employee.empLastName) && Objects.equals(hireDate, employee.hireDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, empFirstName, empLastName, hireDate);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empFirstName='" + empFirstName + '\'' +
                ", empLastName='" + empLastName + '\'' +
                ", empGrade=" + empGrade +
                ", department=" + department +
                ", hireDate=" + hireDate +
                ", termDate=" + termDate +
                ", depManager=" + depManager +
                ", workPlaceLocation=" + workPlaceLocation +
                ", statusEmployee=" + statusEmployee +
                ", vacPlan='" + vacPlan + '\'' +
                ", workSchedule=" + workSchedule +
                ", personalInfo=" + personalInfo +
                ", createdAt=" + createdAt +
                ", addresses=" + addresses +
                ", vacations=" + vacations +
                ", substitutedVacations=" + substitutedVacations +
                ", events=" + events +
                '}';
    }
}
