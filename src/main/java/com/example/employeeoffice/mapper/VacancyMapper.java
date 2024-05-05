package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacanсy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface VacancyMapper {
    @Mapping(target = "position", source = "position")
    @Mapping(target = "vacancyDescription", source = "vacancyDescription")
    @Mapping(target = "vacancyRequirements", source = "vacancyRequirements")
    @Mapping(target = "vacancyContactInfo", source = "vacancyContactInfo")
    @Mapping(target = "department.depName", source = "depName")

    @Mapping(target = "vacancyId", ignore = true)
    @Mapping(target = "vacancySalary", ignore = true)
    @Mapping(target = "workplaceLocation", ignore = true)
    @Mapping(target = "vacancyStatus", ignore = true)
    Vacanсy toEntity(VacancyCreateDto vacancyCreateDto);

    @Mapping(target = "vacancyId", source = "vacancyId")
    VacancyAfterCreationDto toDto(Vacanсy vacancyAfterCreation);
}
