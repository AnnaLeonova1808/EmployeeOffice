package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Vacancy;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.VacancyAlreadyExistsException;
import com.example.employeeoffice.exception.VacancyNotFoundException;
import com.example.employeeoffice.mapper.VacancyMapper;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.repository.VacancyRepository;
import com.example.employeeoffice.service.interfaces.VacancyService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public String deleteVacancyById(UUID vacancyId) {

        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        if (vacancy == null) {
            throw new VacancyNotFoundException(ErrorMessage.VACANCY_NOT_EXIST);
        }

        vacancyRepository.deleteVacancyByVacancyId(vacancyId);
        return "Vacancy with this ID was deleted SUCCESSFULLY";
    }

    @Override
    @Transactional (isolation = Isolation.READ_COMMITTED)
    public VacancyAfterCreationDto createVacancy(VacancyCreateDto vacancyCreateDto) {

        Vacancy vacancy = vacancyRepository.findByVacancyDescription(vacancyCreateDto.getVacancyDescription());
        if (vacancy != null) {
            throw new VacancyAlreadyExistsException(ErrorMessage.VACANCY_ALREADY_EXIST);
        }

        String depName = vacancyCreateDto.getDepName();
        DepartmentName departmentName = DepartmentName.valueOf(depName.toUpperCase());
        Department department = departmentRepository.findByDepName(departmentName);

        if (department == null) {
            department = new Department();
            department.setDepName(departmentName);
            department = departmentRepository.save(department);
        }

        Vacancy entity = vacancyMapper.toEntity(vacancyCreateDto);
        entity.setDepartment(department);
        Vacancy vacancyAfterCreation = vacancyRepository.save(entity);
        return vacancyMapper.toDto(vacancyAfterCreation);
    }
}
