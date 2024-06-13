package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.Position;
import com.example.employeeoffice.entity.enums.VacancyStatus;
import com.example.employeeoffice.entity.enums.WorkPlaceLocation;

import com.example.employeeoffice.generator.UuidTimeSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

/**
 * Entity class for Vacancy.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacancies")
@Schema(description = "Details about job vacancies")
public class Vacancy {

    /**
     * Unique identifier for the vacancy.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            type = UuidTimeSequenceGenerator.class)
    @Column(name = "vacancy_id")
    @Schema(description = "Unique identifier of the vacancy")
    private UUID vacancyId;

    /**
     * Position for the vacancy.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    @Schema(description = "Position for the vacancy")
    private Position position;

    /**
     * Description of the vacancy.
     */
    @Column(name = "vacancy_description")
    @Schema(description = "Description of the vacancy")
    private String vacancyDescription;

    /**
     * Requirements for the vacancy.
     */
    @Column(name = "vacancy_requirements")
    @Schema(description = "Requirements for the vacancy")
    private String vacancyRequirements;

    /**
     * Salary for the vacancy.
     */
    @Column(name = "vacancy_salary")
    @Schema(description = "Salary for the vacancy")
    private double vacancySalary;

    /**
     * Workplace location for the vacancy.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "workplace_location")
    @Schema(description = "Workplace location for the vacancy")
    private WorkPlaceLocation workplaceLocation;

    /**
     * Status of the vacancy.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "vacancy_status")
    @Schema(description = "Status of the vacancy")
    private VacancyStatus vacancyStatus;

    /**
     * Contact information for the vacancy.
     */
    @Column(name = "vacancy_contact_info")
    @Schema(description = "Contact information for the vacancy")
    private String vacancyContactInfo;

    /**
     * Department to which the vacancy belongs.
     * A department can have multiple vacancies.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dep_id")
    @Schema(description = "Department to which the vacancy belongs")
    private Department department;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacanсy = (Vacancy) o;
        return Objects.equals(vacancyId, vacanсy.vacancyId) && position == vacanсy.position && Objects.equals(vacancyDescription, vacanсy.vacancyDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyId, position, vacancyDescription);
    }

    @Override
    public String toString() {
        return "Vacanсy{" +
                "vacancyId=" + vacancyId +
                ", positionTitle=" + position +
                ", vacancyDescription='" + vacancyDescription + '\'' +
                ", vacancyRequirements='" + vacancyRequirements + '\'' +
                ", vacancySalary=" + vacancySalary +
                ", workplaceLocation=" + workplaceLocation +
                ", vacancyStatus=" + vacancyStatus +
                ", vacancyContactInfo='" + vacancyContactInfo + '\'' +
                ", department=" + department +
                '}';
    }
}
