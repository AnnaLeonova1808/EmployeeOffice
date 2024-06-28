package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.enums.AddressType;
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

import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
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

    @Test
    void updateAddressByIdTest() throws Exception{
        UUID testAddressId = UUID.fromString("0b751135-128c-46c9-b554-8c6e05bcd2d8");
        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/address/show_address/" + testAddressId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String addressJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        Address addressBeforeUpdate = objectMapper.readValue(addressJSONBeforeUpdate, Address.class);
        System.out.println("Address Before Update: " + addressBeforeUpdate);

        Address address = ExpectedData.returnUpdateAddress();
        address.setAddressId(testAddressId);
        address.setCity("Berlin");
        address.setStreet("Hauptstrasse");
        address.setHouseNumber("321");
        address.setApartNumber("Wohnung 111");

        String updatedAddressJSON = objectMapper.writeValueAsString(address);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/address/update_address/" + testAddressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAddressJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedAddressFromDB = updateResult.getResponse().getContentAsString();
        Address addressAfterUpdate = objectMapper.readValue(updatedAddressFromDB, Address.class);
        System.out.println("Address After Update: " + addressAfterUpdate);

        assertNotNull(addressAfterUpdate.getAddressId());

        Assertions.assertEquals(address,addressAfterUpdate);

    }

    @Test
    void updateAddressByIdNonExistentIdExc404Test() throws Exception {
        UUID nonExistentAddressId = UUID.fromString("0b751135-128c-46c9-b554-8c6e05bc2288");

        Address address = ExpectedData.returnUpdateAddress();
        address.setAddressId(nonExistentAddressId);
        address.setCity("Berlin");
        address.setStreet("Hauptstrasse");
        address.setHouseNumber("321");
        address.setApartNumber("Wohnung 111");

        String updatedAddressJSON = objectMapper.writeValueAsString(address);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/address/update_address/" + nonExistentAddressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedAddressJSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String responseContent = updateResult.getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);

        Assertions.assertTrue(responseContent.contains("ADDRESS_NOT_FOUND"));
    }
    @Test
    void updateAddressByIdEmptyRequestBodyTest() throws Exception {
        UUID testAddressId = UUID.fromString("0b751135-128c-46c9-b554-8c6e05bcd2d8");

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/address/update_address/" + testAddressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseContent = updateResult.getResponse().getContentAsString();
        System.out.println("Response Content: " + responseContent);

        Assertions.assertTrue(responseContent.contains("Invalid request content."),
                "Response should contain 'Invalid request content.'");
    }
}