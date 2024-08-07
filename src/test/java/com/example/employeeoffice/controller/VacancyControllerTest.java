package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Vacancy;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.entity.enums.Position;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.ListOfVacancyIsEmptyException;
import com.example.employeeoffice.exception.VacancyAlreadyExistsException;
import com.example.employeeoffice.exception.VacancyNotFoundException;
import com.example.employeeoffice.mapper.VacancyMapper;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.repository.VacancyRepository;
import com.example.employeeoffice.service.interfaces.VacancyService;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
public class VacancyControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VacancyRepository vacancyRepository;
    @MockBean
    private VacancyService vacancyService;
    @MockBean
    private DepartmentRepository departmentRepository;
    @MockBean
    private VacancyMapper vacancyMapper;



    @Test
    public void creteVacancyTestPositive() throws Exception {

        VacancyCreateDto vacancyCreateDto = ExpectedData.returnVacancyCreateDto();

        VacancyAfterCreationDto vacancyAfterCreationDto = new VacancyAfterCreationDto();
        vacancyAfterCreationDto.setVacancyId(String.valueOf(UUID.randomUUID()));

        when(vacancyService.createVacancy(any(VacancyCreateDto.class))).thenReturn(vacancyAfterCreationDto);

        String vacancyWrite = new ObjectMapper().writeValueAsString(vacancyCreateDto);

        MvcResult createVacancyResult = mockMvc.perform(MockMvcRequestBuilders.post("/vacancies/create_vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vacancyWrite))
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = createVacancyResult.getResponse().getContentAsString();
        VacancyAfterCreationDto actualDto = new ObjectMapper().readValue(jsonResponse, VacancyAfterCreationDto.class);
        assertNotNull(actualDto.getVacancyId());
        System.out.println(jsonResponse);
    }


    @Test
    public void createVacancy_VacancyAlreadyExists_Negative() throws Exception {

        VacancyCreateDto vacancyCreateAlreadyExistsException = ExpectedData.returnVacancyCreateAlreadyExistsException();

        when(vacancyService.createVacancy(any(VacancyCreateDto.class)))
                .thenThrow(new VacancyAlreadyExistsException(ErrorMessage.VACANCY_ALREADY_EXIST));

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/vacancies/create_vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vacancyCreateAlreadyExistsException)))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"VACANCY_ALREADY_EXIST\",\"errorCode\":\"409 CONFLICT\"}"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    }

    @Test
    void deleteVacancyTestWithException() throws Exception {

        UUID vacancyId = UUID.fromString("61b02a7e-e57c-4321-ba34-73d59bfbddec");

        doThrow(new VacancyNotFoundException(ErrorMessage.VACANCY_NOT_EXIST))
                .when(vacancyService).deleteVacancyById(vacancyId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/vacancies/delete_vacancy/{vacancyId}", vacancyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"VACANCY_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}"))
                .andReturn();
    }

    @Test
    public void createVacancy_DepartmentNotFound_Created() throws Exception {

        VacancyCreateDto vacancyCreateDto = ExpectedData.returnVacancyCreateDto();
        Vacancy vacancy = new Vacancy();
        vacancy.setDepartment(new Department());
        VacancyAfterCreationDto vacancyAfterCreationDto = new VacancyAfterCreationDto();
        vacancyAfterCreationDto.setVacancyId(UUID.randomUUID().toString());

        when(vacancyRepository.findByVacancyDescription(anyString())).thenReturn(null);
        when(departmentRepository.findByDepName(any(DepartmentName.class))).thenReturn(null);
        when(departmentRepository.save(any(Department.class))).thenReturn(new Department());
        when(vacancyMapper.toEntity(any(VacancyCreateDto.class))).thenReturn(vacancy);
        when(vacancyMapper.toDto(any(Vacancy.class))).thenReturn(vacancyAfterCreationDto);
        when(vacancyRepository.save(any(Vacancy.class))).thenReturn(vacancy);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/vacancies/create_vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vacancyCreateDto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void deleteVacancyTestPositive() throws Exception {

        UUID vacancyId = UUID.fromString("51b02a7e-e57c-4321-ba34-73d59bfbddec");

        when(vacancyService.deleteVacancyById(vacancyId))
                .thenReturn("Vacancy with this ID was deleted SUCCESSFULLY");

        mockMvc.perform(MockMvcRequestBuilders.delete("/vacancies/delete_vacancy/{vacancyId}", vacancyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Vacancy with this ID was deleted SUCCESSFULLY"))
                .andReturn().getResponse().getStatus();
    }

    @BeforeEach
    public void setup() {
        List<Vacancy> expectedVacancies = new ArrayList<>(ExpectedData.returnAllVacancies());
        when(vacancyRepository.findAll()).thenReturn(expectedVacancies);
        System.out.println("Mock setup: " + expectedVacancies);
    }

    @Test
    void showAllVacancyPositiveTest() throws Exception {
        Set<Vacancy> vacancySet = ExpectedData.returnAllVacancies();
        List<Vacancy> vacancyList = new ArrayList<>(vacancySet);

        given(vacancyRepository.findAll()).willReturn(vacancyList);
        given(vacancyService.showAllVacancies()).willReturn(
                vacancyList.stream().map(Vacancy::getPosition).map(Position::name).collect(Collectors.toSet())
        );

        Set<String> expectedVacancyNames = vacancySet.stream()
                .map(Vacancy::getPosition)
                .map(Position::name)
                .collect(Collectors.toSet());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/vacancies/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String vacancyListJSON = mvcResult.getResponse().getContentAsString();
        Set<String> actualVacancyNames = new ObjectMapper().readValue(vacancyListJSON, new TypeReference<Set<String>>() {});

        assertEquals(expectedVacancyNames, actualVacancyNames);
    }

    @Test
    void showAllVacancyNegativeTest() throws Exception {

        given(vacancyRepository.findAll()).willReturn(Collections.emptyList());
        willThrow(new ListOfVacancyIsEmptyException(ErrorMessage.LIST_OF_VACANCY_IS_EMPTY))
                .given(vacancyService).showAllVacancies();

        mockMvc.perform(MockMvcRequestBuilders.get("/vacancies/showAll"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_VACANCY_IS_EMPTY))
                .andExpect(jsonPath("$.errorCode").value("404 NOT_FOUND"));
    }
}