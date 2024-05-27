package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacancy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface VacancyMapper {

    @Mapping(target = "vacancyId", ignore = true)
    @Mapping(target = "vacancySalary", ignore = true)
    @Mapping(target = "workplaceLocation", ignore = true)
    @Mapping(target = "vacancyStatus", ignore = true)
    @Mapping(target = "department", ignore = true) // Игнорируем поле department

    @Mapping(target = "position", source = "position")
    @Mapping(target = "vacancyDescription", source = "vacancyDescription")
    @Mapping(target = "vacancyRequirements", source = "vacancyRequirements")
    @Mapping(target = "vacancyContactInfo", source = "vacancyContactInfo")
    Vacancy toEntity(VacancyCreateDto vacancyCreateDto);

    @Mapping(target = "vacancyId", source = "vacancyId")
    VacancyAfterCreationDto toDto(Vacancy vacancyAfterCreation);
}
