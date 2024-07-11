package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Authority;
import com.example.employeeoffice.entity.enums.AuthorityName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.AuthorityRepository;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
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
public class AuthorityControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    AuthorityRepository authorityRepository;

    @Test
    void showAllAuthorityPositiveTest() throws Exception {

        Set<Authority> authoritySet = ExpectedData.returnAllAuthorities();
        List<Authority> authorityList = List.copyOf(authoritySet);

        given(authorityRepository.findAll()).willReturn(authorityList);

        Set<String> expectedAuthorityNames = authoritySet.stream()
                .map(Authority::getAuthority)
                .map(AuthorityName::name)
                .collect(Collectors.toSet());

        Set<String> actualAuthorityNames = showAll();
        assertEquals(expectedAuthorityNames, actualAuthorityNames);
    }
    @Test
    void showAllAuthorityNegativeTest() throws Exception {
        given(authorityRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/authorities/showAllAuthority"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_AUTHORITY_IS_EMPTY))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    Set<String> showAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/authorities/showAllAuthority"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String authorityListJSON = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(authorityListJSON, new TypeReference<Set<String>>() {});
    }
}
