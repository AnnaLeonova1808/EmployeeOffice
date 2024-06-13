package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting between VacancyCreateDto and Vacancy entities.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface VacancyMapper {

    /**
     * Converts VacancyCreateDto to Vacancy entity.
     *
     * @param vacancyCreateDto the DTO containing vacancy creation data
     * @return the Vacancy entity
     */
    @Mapping(target = "vacancyId", ignore = true)
    @Mapping(target = "vacancySalary", ignore = true)
    @Mapping(target = "workplaceLocation", ignore = true)
    @Mapping(target = "vacancyStatus", ignore = true)
    @Mapping(target = "department", ignore = true)

    @Mapping(target = "position", source = "position")
    @Mapping(target = "vacancyDescription", source = "vacancyDescription")
    @Mapping(target = "vacancyRequirements", source = "vacancyRequirements")
    @Mapping(target = "vacancyContactInfo", source = "vacancyContactInfo")
    Vacancy toEntity(VacancyCreateDto vacancyCreateDto);

    /**
     * Converts Vacancy entity to VacancyAfterCreationDto.
     *
     * @param vacancyAfterCreation the Vacancy entity
     * @return the DTO containing vacancy details after creation
     */
    @Mapping(target = "vacancyId", source = "vacancyId")
    VacancyAfterCreationDto toDto(Vacancy vacancyAfterCreation);
}
