package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.RoleIdNotFoundException;
import com.example.employeeoffice.exception.RoleNotFoundException;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.employeeoffice.utils.ExpectedData.returnAllRoles;
import static java.util.Arrays.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class RoleControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    private final UUID roleId = UUID.randomUUID();
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

    @Test
    void getRoleByIdPositiveTest() throws Exception {
        UUID roleId = UUID.fromString("64d1e267-7034-4c72-989b-0e3214f264ce");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/role/get/{roleId}", roleId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(200, result.getResponse().getStatus());
        Assertions.assertTrue(jsonResponse.contains(roleId.toString()));

    }

    @Test
    void getRoleByIdNegativeTest() throws Exception {
        String nonExistentID = "44d1e267-7034-4c72-989b-0e3214f264ce";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/role/get/{role_id}", nonExistentID))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"ROLE_ID_NOT_FOUND_EXCEPTION\",\"errorCode\":\"NOT_FOUND\"}"));
    }

    @Test
    void testGetAllRolesNamesPositive() throws Exception {
        Set<String> expectedRoles = ExpectedData.returnAllRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.toSet());

        MvcResult roleResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/role/rolesNames"))
                .andExpect(status().isOk())
                .andReturn();

        String roleResultJSON = roleResult.getResponse().getContentAsString();
        Set<String> roleNames = objectMapper.readValue(roleResultJSON, new TypeReference<Set<String>>() {
        });

        Assertions.assertEquals(expectedRoles, roleNames);
    }

    @Test
    void testGetAllRolesNamesNegative() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/role/rolesNames"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"ROLE_USER\",\"ROLE_GUEST\",\"ROLE_MANAGER\",\"ROLE_ADMIN\"]"));
    }

}