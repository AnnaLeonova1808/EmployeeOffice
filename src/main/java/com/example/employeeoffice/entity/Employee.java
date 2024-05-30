package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.Position;
import com.example.employeeoffice.entity.enums.StatusEmployee;
import com.example.employeeoffice.entity.enums.WorkPlaceLocation;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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

/**
 * This class represents an employee in the employee office system.
 * It contains information about a specific employee and related data.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
@Schema(description = "This class represents an employee in the employee office system.")
public class Employee {

    /**
     * Unique identifier of the employee.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "emp_id")
    @Schema(description = "Unique identifier of the employee")
    private UUID empId;

    /**
     * First name of the employee.
     */
    @Column(name = "first_name")
    @Schema(description = "First name of the employee")
    private String firstName;

    /**
     * Last name of the employee.
     */
    @Column(name = "last_name")
    @Schema(description = "Last name of the employee")
    private String lastName;

    /**
     * Position of the employee in the company.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    @Schema(description = "Position of the employee in the company")
    private Position position;

    /**
     * Date when the employee was hired.
     */
    @Column(name = "hire_date")
    @Schema(description = "Date when the employee was hired")
    private LocalDate hireDate;

    /**
     * Date when the employee's term ended.
     */
    @Column(name = "term_date")
    @Schema(description = "Date when the employee's term ended")
    private LocalDate termDate;

    /**
     * Location of the employee's workplace.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "workplace_location")
    @Schema(description = "Location of the employee's workplace")

    private WorkPlaceLocation workPlaceLocation;

    /**
     * Current status of the employee.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status_emp")
    @Schema(description = "Current status of the employee")
    private StatusEmployee statusEmp;

    /**
     * Timestamp when the employee record was created.
     */

    @Column(name = "created_at")
    @Schema(description = "Timestamp when the employee record was created")
    private Timestamp createdAt;

    /**
     * Vacation plan of the employee.
     */
    @Column(name = "vac_plan")
    @Schema(description = "Vacation plan of the employee")
    private String vacPlan;

    /**
     * Department to which the employee belongs.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id")
    @Schema(description = "Department to which the employee belongs")
    private Department department;

    /**
     * Manager of the department to which the employee belongs.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_manager_id")
    @Schema(description = "Manager of the department to which the employee belongs")
    private Employee depManager;

    /**
     * Department managed by the employee.
     */
    @JsonIgnore
    @OneToOne(mappedBy = "depManager")
    @Schema(description = "Department managed by the employee")
    private Department managedDepartment;

    /**
     * Work schedule assigned to the employee.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sched_id")
    @Schema(description = "Work schedule assigned to the employee")
    private WorkSchedule workSchedule;

    /**
     * Personal information of the employee.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pers_info_id")
    @Schema(description = "Personal information of the employee")
    private PersonalInfo persInfo;

    /**
     * List of vacations taken by the employee.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "employee", orphanRemoval = true, fetch = FetchType.LAZY)
    @Schema(description = "List of vacations taken by the employee")
    private Set<Vacation> vacations;

    /**
     * List of vacations during which the employee substituted for someone.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "substitutionEmp", orphanRemoval = true, fetch = FetchType.LAZY)
    @Schema(description = "List of vacations during which the employee substituted for someone")
    private Set<Vacation> substitutedVacations;

    /**
     * List of events the employee is participating in.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "events_employee",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "ev_id"))
    @Schema(description = "List of events the employee is participating in")
    private Set<Event> events;


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
                ", position=" + position +
                ", hireDate=" + hireDate +
                ", termDate=" + termDate +
                ", workPlaceLocation=" + workPlaceLocation +
                ", statusEmp=" + statusEmp +
                ", createdAt=" + createdAt +
                ", vacPlan='" + vacPlan + '\'' +
                ", department=" + department +
                ", depManager=" + depManager +
                ", managedDepartment=" + managedDepartment +
                ", workSchedule=" + workSchedule +
                ", persInfo=" + persInfo +
                ", vacations=" + vacations +
                ", substitutedVacations=" + substitutedVacations +
                ", events=" + events +
                '}';
    }
}
