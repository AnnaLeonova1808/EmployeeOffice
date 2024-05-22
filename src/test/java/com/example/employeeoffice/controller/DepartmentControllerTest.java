package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.service.interfaces.DepartmentService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class DepartmentControllerTest {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void showDepartmentByName() throws Exception{
        Department expectedDepartment = ExpectedData.returnDepartmentByName();

        String departmentJson = objectMapper.writeValueAsString(expectedDepartment);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/department/show_department_by_name/{depName}", expectedDepartment.getDepName())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(departmentJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualDepartmentJSON = mvcResult.getResponse().getContentAsString();

        Department actualDepartment = objectMapper.readValue(actualDepartmentJSON, Department.class);
        System.out.println("Expected department: " + expectedDepartment);
        System.out.println("Actual department: " + actualDepartment);
        Assertions.assertEquals(expectedDepartment, actualDepartment);

    }
    @Test
    void showDepartmentByNameTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/show_department_by_name/INVALID_DEPARTMENT_NAME"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"DEPARTMENT_NOT_EXIST\",\"errorCode\":\"NOT_FOUND\"}"));


    }
    }