package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.entity.enums.Position;
import com.example.employeeoffice.entity.enums.StatusEmployee;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.EmployeeRepository;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
class EmployeeControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void getEmployeeByIdTest() throws Exception {
        Employee expectedEmployee = ExpectedData.returnEmployeeById();

        String employeeJson = objectMapper.writeValueAsString(expectedEmployee);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/employee/get/{empId}", expectedEmployee.getEmpId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(employeeJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualEmployeeJSON = mvcResult.getResponse().getContentAsString();

        Employee actualEmployee = objectMapper.readValue(actualEmployeeJSON, Employee.class);
        System.out.println("Expected employee: " + expectedEmployee);
        System.out.println("Actual employee: " + actualEmployee);
        Assertions.assertEquals(expectedEmployee, actualEmployee);

    }
    @Test
    void getEmployeeByIdTestWithException() throws Exception {

        String expectedErrorMessage = "{\"message\":\"EMPLOYEE_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/get/7270910c-cc71-4634-97a0-a242eb5b6066")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedErrorMessage));
    }

    @Test
    void deleteEmployeeByIdTestPositive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/7881bf3e-73a9-47da-8bae-e2e253a30ddd"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Employee with this ID was deleted SUCCESSFULLY"))
                .andReturn();
    }
    @Test
    void deleteEmployeeByIdTestWithException() throws Exception {
        String expectedErrorMessage = "{\"message\":\"EMPLOYEE_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/employee/delete/8881bf3e-73a9-47da-8bae-e2e253a30ddd")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedErrorMessage))
                .andReturn();
    }

    @Test
    void createEmployeeTest() throws Exception {
        EmployeeRegistrationDto employeeRegistrationDto = ExpectedData.returnEmployeeRegistrationDto();
        String employeeWrite = objectMapper.writeValueAsString(employeeRegistrationDto);

        MvcResult createEmployeeResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/employee/registration/create_employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeWrite))
                .andReturn();

        String jsonResult = createEmployeeResult.getResponse().getContentAsString();
        EmployeeAfterRegistrationDto employeeAfterRegistrationDto = objectMapper.readValue(jsonResult, EmployeeAfterRegistrationDto.class);

        Assertions.assertEquals(201, createEmployeeResult.getResponse().getStatus());
        Assertions.assertNotNull(employeeAfterRegistrationDto.getEmpId());

        System.out.println(jsonResult);
    }
    @Test
    void createEmployeePasswordEncryptionCheckTest() throws Exception {
        EmployeeRegistrationDto employeeRegistrationDto = ExpectedData.returnEmployeeRegistrationDto();
        String employeeWrite = objectMapper.writeValueAsString(employeeRegistrationDto);

        MvcResult createEmployeeResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/employee/registration/create_employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeWrite))
                .andReturn();

        String jsonResult = createEmployeeResult.getResponse().getContentAsString();
        EmployeeAfterRegistrationDto employeeAfterRegistrationDto = objectMapper.readValue(jsonResult, EmployeeAfterRegistrationDto.class);

        Assertions.assertEquals(201, createEmployeeResult.getResponse().getStatus());
        Assertions.assertNotNull(employeeAfterRegistrationDto.getEmpId());

        String originalPassword = employeeRegistrationDto.getPassword();
        String encodedPassword = employeeAfterRegistrationDto.getPassword();
        Assertions.assertTrue(passwordEncoder.matches(originalPassword, encodedPassword), "Password should be encrypted");

        System.out.println(jsonResult);
    }
    @Test
    void showAllEmployeePositiveTest() throws Exception {

        Set<Employee> employeeSet = ExpectedData.returnAllEmployees();
        List<Employee> employeeList = List.copyOf(employeeSet);

        given(employeeRepository.findAll()).willReturn(employeeList);
        MvcResult mvcResult = mockMvc.perform(get("/employee/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String employeeListJSON = mvcResult.getResponse().getContentAsString();
        List<Employee> actualEmployeeList = objectMapper.readValue(employeeListJSON, new TypeReference<List<Employee>>() {});

        Set<String> expectedEmployeeNames = employeeSet.stream()
                .map(Employee::getLastName)
                .collect(Collectors.toSet());

        Set<String> actualEmployeeNames = actualEmployeeList.stream()
                .map(Employee::getLastName)
                .collect(Collectors.toSet());

        assertEquals(expectedEmployeeNames, actualEmployeeNames);
    }
    @Test
    void showAllEmployeeNegativeTest() throws Exception {
        given(employeeRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/employee/showAll"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_EMPLOYEE_IS_EMPTY))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    Set<String> showAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/employee/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String employeeListJSON = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(employeeListJSON, new TypeReference<Set<String>>() {});
    }

}