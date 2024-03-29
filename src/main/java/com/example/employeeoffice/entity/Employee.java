package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.EmployeeGrade;
import com.example.employeeoffice.entity.enums.StatusEmployee;
import com.example.employeeoffice.entity.enums.WorkPlaceLocation;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "emp_id")
    private UUID empId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "emp_grade")
    private EmployeeGrade empGrade;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "term_date")
    private LocalDate termDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "workplace_location")
    private WorkPlaceLocation workPlaceLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_emp")
    private StatusEmployee statusEmp;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "vac_plan")
    private String vacPlan;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id")
    private Department department; // ! связь с департаментом, 1) для определения руководителя(11) и 2) для определения списка кто входит в департамент из сотрудников(4)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_manager_id")
    private Employee depManager; // (11)Руководитель сотрудника (или руководитель департамента)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id")
    private WorkSchedule workSchedule; // (6) Связь с графиком работы: у сотрудника может быть один график работы,
    // а у одного графика работы может быть несколько сотрудников

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pers_info_id")
    private PersonalInfo persInfo; //(2) у каждого сотрудника может быть только одна личная информация

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Address> addresses; // (8) Один сотрудник может иметь несколько адресов (например, домашний и рабочий адрес)

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Vacation> vacations; // (7) один сотрудник может иметь несколько записей об отпусках.

    @OneToMany(mappedBy = "substitutionEmp", fetch = FetchType.LAZY)
    private Set<Vacation> substitutedVacations; //(10) связь между сотрудником, который замещает другого во время отпуска и отпуском

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_employee",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;  // (9)  На разные события (корпоратив, конференция) приглашают несколько сотрудников


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empGrade=" + empGrade +
                ", hireDate=" + hireDate +
                ", termDate=" + termDate +
                ", workPlaceLocation=" + workPlaceLocation +
                ", statusEmp=" + statusEmp +
                ", createdAt=" + createdAt +
                ", vacPlan='" + vacPlan + '\'' +
                ", department=" + department +
                ", depManager=" + depManager +
                ", workSchedule=" + workSchedule +
                ", persInfo=" + persInfo +
                ", addresses=" + addresses +
                ", vacations=" + vacations +
                ", substitutedVacations=" + substitutedVacations +
                ", events=" + events +
                '}';
    }
}
