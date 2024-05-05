package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Address;
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

import static org.junit.jupiter.api.Assertions.*;
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
    void getEmployeeById() throws Exception {
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
    void deleteEmployeeById() {
    }

    @Test
    void createEmployee() {
    }
}