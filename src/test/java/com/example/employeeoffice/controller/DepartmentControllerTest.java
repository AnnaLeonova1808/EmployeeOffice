package com.example.employeeoffice.controller;

import com.example.employeeoffice.controller.handler.ErrorExtension;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.ErrorResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

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
    @MockBean
    DepartmentRepository departmentRepository;


    @Test
    void showDepartmentByName() throws Exception {
        Department expectedDepartment = ExpectedData.returnDepartmentByName();

        Mockito.when(departmentRepository.findByDepName(DepartmentName.IT)).thenReturn(expectedDepartment);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/department/show_department_by_name/IT", expectedDepartment.getDepName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualDepartmentJSON = mvcResult.getResponse().getContentAsString();
        Department actualDepartment = objectMapper.readValue(actualDepartmentJSON, Department.class);

        System.out.println("Expected department: " + expectedDepartment);
        System.out.println("Actual department: " + actualDepartment);

        Assertions.assertEquals(expectedDepartment, actualDepartment);
    }
//    @Test
//    void showDepartmentByNameTestWithException() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/department/show_department_by_name/Production")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json("{\"message\":\"DEPARTMENT_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}"))
//                .andReturn();
//    }
//    @Test
//    void showDepartmentByNameTestWithValidName() throws Exception {
//
//        Department expectedDepartment = ExpectedData.returnDepartmentByName();
//
//        when(departmentRepository.findByDepName(DepartmentName.IT)).thenReturn(expectedDepartment);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                        .get("/department/show_department_by_name/{depName}", expectedDepartment.getDepName())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String actualDepartmentJSON = mvcResult.getResponse().getContentAsString();
//        Department actualDepartment = objectMapper.readValue(actualDepartmentJSON, Department.class);
//
//        Assertions.assertEquals(expectedDepartment, actualDepartment);
//    }
//@Test
//void showDepartmentByNameTestWithValidName() throws Exception {
//    Department expectedDepartment = ExpectedData.returnDepartmentByName();
//
//    when(departmentRepository.findByDepName(DepartmentName.IT)).thenReturn(expectedDepartment);
//
//    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//                    .get("/department/show_department_by_name/IT")
//                    .contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andReturn();
//
//    String actualDepartmentJSON = mvcResult.getResponse().getContentAsString();
//    Department actualDepartment = objectMapper.readValue(actualDepartmentJSON, Department.class);
//
//    Assertions.assertEquals(expectedDepartment, actualDepartment);
//}

    @Test
    void showDepartmentByNameTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/show_department_by_name/PRODUCTION"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"DEPARTMENT_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}"));
    }
}