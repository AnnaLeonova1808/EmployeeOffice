package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
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

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
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

    @Test
    void showDepartmentByNameTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/department/show_department_by_name/PRODUCTION"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"DEPARTMENT_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}"));
    }

    @Test
    void showAllDepartmentPositiveTest() throws Exception {

        Set<Department> departmentSet = ExpectedData.returnAllDepartments();
        List<Department> departmentList = List.copyOf(departmentSet);

        given(departmentRepository.findAll()).willReturn(departmentList);

        Set<String> expectedDepartmentNames = departmentSet.stream()
                .map(Department::getDepName)
                .map(DepartmentName::name)
                .collect(Collectors.toSet());

        Set<String> actualDepartmentNames = showAll();
        assertEquals(expectedDepartmentNames, actualDepartmentNames);
    }
    @Test
    void showAllDepartmentNegativeTest() throws Exception {
        given(departmentRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/department/showAll"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_DEPARTMENT_IS_EMPTY))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    Set<String> showAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/department/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String departmentListJSON = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(departmentListJSON, new TypeReference<Set<String>>() {});
    }

}