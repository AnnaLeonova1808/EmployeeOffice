package com.example.employeeoffice.controller;

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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
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
        String expectedErrorMessage = "{\"message\":\"ROLE_ID_NOT_FOUND_EXCEPTION\",\"errorCode\":\"404 NOT_FOUND\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/role/get/{role_id}", nonExistentID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedErrorMessage));
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