package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a department in the employee office system.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departments")
@Schema(description = "Represents a department in the employee office system.")
public class Department {

    /**
     * Unique identifier of the department.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "dep_id")
    @Schema(description = "Unique identifier of the department")
    private UUID depId;

    /**
     * Name of the department.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "dep_name")
    @Schema(description = "Name of the department")
    private DepartmentName depName;

    /**
     * Phone number of the department.
     */
    @Column(name = "dep_phone")
    @Schema(description = "Phone number of the department")
    private String depPhone;

    /**
     * Email address of the department.
     */
    @Column(name = "dep_email")
    @Schema(description = "Email address of the department")
    private String depEmail;

    /**
     * Manager of the department.
     */
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "dep_manager_id")
    @Schema(description = "Manager of the department")
    private Employee depManager;

    /**
     * Employees in the department.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @Schema(description = "Employees in the department")
    private Set<Employee> employees;

    /**
     * Vacancies in the department.
     */
    @OneToMany(mappedBy = "department", orphanRemoval = true, fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @Schema(description = "Vacancies in the department")
    private Set<Vacancy> vacancies; // (5)  в одном отделе может быть несколько вакансий. Связь с вакансиями в отделе

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(depId, that.depId) && depName == that.depName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depId, depName);
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depId +
                ", depName=" + depName +
                ", depPhone='" + depPhone + '\'' +
                ", depEmail='" + depEmail + '\'' +
                ", depManager=" + depManager +
                ", employees=" + employees +
                ", vacancies=" + vacancies +
                '}';
    }
}
