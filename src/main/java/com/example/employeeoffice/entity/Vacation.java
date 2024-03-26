package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.VacationCategory;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacations")
public class Vacation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "vac_id")
    private UUID vacId;

    @Column(name = "vac_start_date")
    private LocalDate vacStartDate;

    @Column(name = "vac_end_date")
    private LocalDate vacEndDate;

    @Column(name = "vac_type")
    private boolean vacType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vac_category")
    private VacationCategory vacCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "substitution_emp_id")
    private Employee substitutionEmp; //(10) связь между сотрудником, который замещает другого во время отпуска и отпуском

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id") // (7) Связь с сущностью Employee
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
