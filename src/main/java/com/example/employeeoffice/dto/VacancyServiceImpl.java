package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.Vacanсy;
import com.example.employeeoffice.mapper.VacancyMapper;
import com.example.employeeoffice.repository.VacancyRepositoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyServiceDto{
    private final VacancyRepositoryDto vacancyRepositiryDto;
    private final VacancyMapper vacancyMapper;
    @Override
    public VacancyAfterCreationDto createVacancy(VacancyCreateDto vacancyCreateDto) {
        Vacanсy vacanсy = vacancyRepositiryDto.findByVacancyDescription(vacancyCreateDto.getVacancyDescription());
        if (vacanсy != null){
            throw new NullPointerException();
        }
        Vacanсy entity = vacancyMapper.toEntity(vacancyCreateDto);
        Vacanсy vacancyAfterCreation = vacancyRepositiryDto.save(entity);
        return vacancyMapper.toDto(vacancyAfterCreation);
    }
}
