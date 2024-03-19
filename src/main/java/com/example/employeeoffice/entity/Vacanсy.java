package com.example.employeeoffice.entity;

import com.example.employeeoffice.entity.enums.EmploymentType;
import com.example.employeeoffice.entity.enums.Position;
import com.example.employeeoffice.entity.enums.VacancyStatus;
import com.example.employeeoffice.entity.enums.WorkPlaceLocation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "vacancy")

public class Vacanсy {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "vacancy_id", columnDefinition = "BINARY(16)")
    private UUID vacancyId;
    @Enumerated(EnumType.STRING)
    @Column(name = " position_title")
    private Position positionTitle;
    @Column(name = "vacancy_description")
    private String vacancyDescription;
    @Column(name = "vacancy_requirements")
    private String vacancyRequirements;
    @Column(name = "vacancy_salary")
    private double vacancySalary;
    @Enumerated(EnumType.STRING)
    @Column(name = "workplace_location")
    private WorkPlaceLocation workplaceLocation;
    @Enumerated(EnumType.STRING)
    @Column(name = "vacancy_status")
    private VacancyStatus vacancyStatus;
    @Column(name = "vacancy_contact_information")
    private String vacancyContactInformation;
    @Enumerated(EnumType.STRING)
    @Column(name = "employment_type")
    private EmploymentType employmentType;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department depName; // (5) Одна вакансия (Vacancy) принадлежит только к одному отделу (Department). Связь с отделом, к которому относится вакансия


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacanсy)) return false;
        Vacanсy vacanсy = (Vacanсy) o;
        return Objects.equals(vacancyId, vacanсy.vacancyId) && positionTitle == vacanсy.positionTitle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyId, positionTitle);
    }

    @Override
    public String toString() {
        return "Vacanсy{" +
                "vacancyId=" + vacancyId +
                ", positionTitle=" + positionTitle +
                ", vacancyDescription='" + vacancyDescription + '\'' +
                ", vacancyRequirements='" + vacancyRequirements + '\'' +
                ", vacancySalary=" + vacancySalary +
                ", workplaceLocation=" + workplaceLocation +
                ", vacancyStatus=" + vacancyStatus +
                ", vacancyContactInformation='" + vacancyContactInformation + '\'' +
                ", employmentType=" + employmentType +
                '}';
    }
}
