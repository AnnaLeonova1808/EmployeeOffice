package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacanсy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VacancyMapper {
    Vacanсy toEntity(VacancyCreateDto vacancyCreateDto);

    VacancyAfterCreationDto toDto(Vacanсy vacancyAfterCreation);
}
