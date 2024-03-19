package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.VacationCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vacation")

public class Vacation {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "vacation_id", columnDefinition = "BINARY(16)")
    private UUID vacId;
    @Column(name = "vacation_start_date")
    private LocalDate vacStartDate;
    @Column(name = "vacation_end_date")
    private LocalDate vacEndDate;
    @Column(name = "vacation_type")
    private boolean vacType;
    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_category")
    private VacationCategory vacCategory;
    @ManyToOne
    @JoinColumn(name = "substitution_emp_id")
    private Employee substitutionEmp; //(10) связь между сотрудником, который замещает другого во время отпуска и отпуском
    @ManyToOne
    @JoinColumn(name = "employee_id") // (7) Связь с сущностью Employee
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacation)) return false;
        Vacation vacation = (Vacation) o;
        return Objects.equals(vacId, vacation.vacId) && Objects.equals(substitutionEmp, vacation.substitutionEmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacId, substitutionEmp);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "vacId=" + vacId +
                ", vacStartDate=" + vacStartDate +
                ", vacEndDate=" + vacEndDate +
                ", vacType=" + vacType +
                ", vacCategory=" + vacCategory +
                ", substitutionEmp=" + substitutionEmp +
                ", employee=" + employee +
                '}';
    }
}
