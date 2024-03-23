package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "departments")

public class Department {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "com.example.employeeOffice.generator.UuidTimeSequenceGenerator")
    @Column(name = "dep_id")
    private UUID depId;

    @Enumerated(EnumType.STRING)
    @Column(name = "dep_name")
    private DepartmentName depName;

    @Column(name = "dep_phone")
    private String depPhone;

    @Column(name = "dep_email")
    private String depEmail;

    @OneToMany(mappedBy = "departments")
    private Set<Employee> employees; // (4) Связь с сотрудниками отдела

    @OneToMany(mappedBy = "dep_manager_id")
    private Set<Employee> managerEmployees; // Связь с сотрудниками, которые находятся под управлением этого департамента// (11) Связь с руководителем департамента, который кто то из сотрудников и связь сотрудника с руководителем

    @OneToMany(mappedBy = "departments")
    private Set<Vacanсy> vacancies; // (5)  в одном отделе может быть несколько вакансий. Связь с вакансиями в отделе

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
                ", employees=" + employees +
                ", managerEmployees=" + managerEmployees +
                ", vacancies=" + vacancies +
                '}';
    }
}
