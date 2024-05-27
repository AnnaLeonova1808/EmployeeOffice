package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class EmployeeControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;


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

}