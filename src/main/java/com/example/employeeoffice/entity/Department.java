package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.DepartmentName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "department_id", columnDefinition = "BINARY(16)")
    private UUID depId;
    @Enumerated(EnumType.STRING)
    @Column(name = "department_name")
    private DepartmentName depName;
    @ManyToOne
    @JoinColumn(name = "department_manager_id")
    private Employee depManager;  //  ! (11) Связь с руководителем департамента, который кто то из сотрудников и связь сотрудника с руководителем

    @Column(name = "phone_number_departament")
    private String phoneNumberDepartament;
    @Column(name = "email_departament")
    private String emailDepartament;

    @OneToMany(mappedBy = "department")
    private Set<Vacanсy> vacancies; // (5)  в одном отделе может быть несколько вакансий. Связь с вакансиями в отделе

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees; // (4) Связь с сотрудниками отдела

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
                ", depManager=" + depManager +
                ", phoneNumberDepartament='" + phoneNumberDepartament + '\'' +
                ", emailDepartament='" + emailDepartament + '\'' +
                ", vacancies=" + vacancies +
                ", employees=" + employees +
                '}';
    }
}
