package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.VacationCategory;
import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
/**
 * Entity class for Vacation.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacations")
@Schema(description = "Details about employee vacations")
public class Vacation {
    /**
     * Unique identifier for the vacation.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "vac_id")
    @Schema(description = "Unique identifier of the vacation")
    private UUID vacId;

    /**
     * Start date of the vacation.
     */
    @Column(name = "vac_start_date")
    @Schema(description = "Start date of the vacation")
    private LocalDate vacStartDate;

    /**
     * End date of the vacation.
     */
    @Column(name = "vac_end_date")
    @Schema(description = "End date of the vacation")
    private LocalDate vacEndDate;

    /**
     * Type of the vacation (true for paid, false for unpaid).
     */
    @Column(name = "vac_type")
    @Schema(description = "Type of the vacation (true for paid, false for unpaid)")
    private boolean vacType;

    /**
     * Category of the vacation.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "vac_category")
    @Schema(description = "Category of the vacation")
    private VacationCategory vacCategory;

    /**
     * Employee substituting for the vacationing employee.
     * Relationship between an employee substituting another during vacation and the vacation itself.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "substitution_emp_id")
    @Schema(description = "Employee substituting for the vacationing employee")
    private Employee substitutionEmp;

    /**
     * Employee who is taking the vacation.
     * Relationship with the Employee entity.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    @Schema(description = "Employee who is taking the vacation")
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
