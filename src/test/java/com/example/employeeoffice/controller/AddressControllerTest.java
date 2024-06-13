package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.utils.ExpectedData;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "emily", password = "222", roles = "USER")
class AddressControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void showAddressByIdTest() throws Exception {

        Address expectedAddress = ExpectedData.returnAddress();

        String addressJson = objectMapper.writeValueAsString(expectedAddress);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/address/show_address/{addressId}", expectedAddress.getAddressId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(addressJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualAddressJSON = mvcResult.getResponse().getContentAsString();

        Address actualAddress = objectMapper.readValue(actualAddressJSON, Address.class);
        System.out.println("Expected address: " + expectedAddress);
        System.out.println("Actual address: " + actualAddress);
        Assertions.assertEquals(expectedAddress, actualAddress);

    }

    @Test
    void showAddressByIdTestWithException() throws Exception {

        String expectedErrorMessage = "{\"message\":\"ADDRESS_NOT_EXIST\",\"errorCode\":\"404 NOT_FOUND\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/address/show_address/b23a92eb-398c-4ba9-9680-b4b3a72a911d")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedErrorMessage));
    }
}