package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Vacanсy;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.VacancyAlreadyExistsException;
import com.example.employeeoffice.exception.VacancyNotFoundExeption;
import com.example.employeeoffice.mapper.VacancyMapper;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.repository.VacancyRepository;
import com.example.employeeoffice.service.interfaces.VacancyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public String deleteVacancyById(UUID vacancyId) {

        Vacanсy vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        if (vacancy == null) {
            throw new VacancyNotFoundExeption(ErrorMessage.VACANCY_NOT_EXIST);
        }

        vacancyRepository.deleteVacancyByVacancyId(vacancyId);
        return "Vacancy with this ID was deleted SUCCESSFULLY";
    }

    @Override
    @Transactional
    public VacancyAfterCreationDto createVacancy(VacancyCreateDto vacancyCreateDto) {

        Vacanсy vacancy = vacancyRepository.findByVacancyDescription(vacancyCreateDto.getVacancyDescription());
        if (vacancy != null) {
            throw new VacancyAlreadyExistsException(ErrorMessage.VACANCY_ALREADY_EXIST);

        }
        String depName = vacancyCreateDto.getDepName();
        DepartmentName departmentName = DepartmentName.valueOf(depName.toUpperCase());
        Department department = departmentRepository.findByDepName(departmentName);
        if (department == null){
            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);
        }

        Vacanсy entity = vacancyMapper.toEntity(vacancyCreateDto);
        entity.setDepartment(department);
        Vacanсy vacancyAfterCreation = vacancyRepository.save(entity);
        return vacancyMapper.toDto(vacancyAfterCreation);
    }
}
