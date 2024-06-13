package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.PersonalInfo;
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

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
class PersonalInfoControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getPersonalInfoByIdTest() throws Exception{

        PersonalInfo expectedPersonalInfo = ExpectedData.returnPersonalInfo();

        String personalInfoJson = objectMapper.writeValueAsString(expectedPersonalInfo);

        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                                .get("/personal_info/getPersInfo/{persInfoId}", expectedPersonalInfo.getPersInfoId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(personalInfoJson))
                        .andExpect(status().isOk())
                        .andReturn();

        String actualPersonalInfoJSON = mvcResult.getResponse().getContentAsString();

        PersonalInfo actualPersonalInfo = objectMapper.readValue(actualPersonalInfoJSON, PersonalInfo.class);
        System.out.println("Expected PersonalInfo: " + expectedPersonalInfo);
        System.out.println("Actual PersonalInfo: " + actualPersonalInfo);

        Assertions.assertEquals(expectedPersonalInfo, actualPersonalInfo);

    }
    @Test
    void showPersonalInfoByIdTestWithException() throws Exception {

        mockMvc.perform(get("/personal_info/getPersInfo/1f486486-97dc-4f50-8fb1-cd87d5dd37e2"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }


    @Test
    void updatePersonalInfoByIdTest() throws Exception{

        MvcResult mvcResultBeforeUpdate = mockMvc
                .perform(MockMvcRequestBuilders.get("/personal_info/getPersInfo/1f486486-97dc-4f50-8fb1-cd87d5dd37e1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String personalInfoJSONBeforeUpdate = mvcResultBeforeUpdate.getResponse().getContentAsString();
        PersonalInfo personalInfoBeforeUpdate = objectMapper.readValue(personalInfoJSONBeforeUpdate, PersonalInfo.class);
        System.out.println("Personal Info Before Update: " + personalInfoBeforeUpdate);

        PersonalInfo personalInfo = ExpectedData.returnPersonalInfo();
        personalInfo.setSalary(55000);

        String updatedPersonalInfoJSON = objectMapper.writeValueAsString(personalInfo);

        MvcResult updateResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/personal_info/update_persInfo/1f486486-97dc-4f50-8fb1-cd87d5dd37e1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedPersonalInfoJSON))
                .andExpect(status().isOk())
                .andReturn();

        String updatedPersonalInfoFromDB = updateResult.getResponse().getContentAsString();
        PersonalInfo personalInfoAfterUpdate = objectMapper.readValue(updatedPersonalInfoFromDB, PersonalInfo.class);
        System.out.println("Personal Info After Update: " + personalInfoAfterUpdate);

        assertNotNull(personalInfoAfterUpdate.getPassword());

        Assertions.assertEquals(personalInfo,personalInfoAfterUpdate);

    }
    @Test
    void updatePersonalInfoByIdWithException() throws Exception{

        String nonExistId = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2";
        String requestBody = "{\"salary\": 55000.00}";

        mockMvc.perform(MockMvcRequestBuilders.put("/personal_info/update_persInfo/" + nonExistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON));
    }
    @Test
    void showAllPersonalInfoByRoleName() throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/personal_info/roles/ROLE_USER")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        List<PersonalInfo> personalInfoList = objectMapper.readValue(jsonResponse, new TypeReference<List<PersonalInfo>>() {});

        Assertions.assertEquals(200, result.getResponse().getStatus());
        Assertions.assertFalse(personalInfoList.isEmpty());
    }
    @Test
    void showAllPersonalInfoByRoleNameNegative() throws Exception {

        String expectedErrorMessage = "{\"message\":\"Invalid role name: INVALID_ROLE\",\"errorCode\":\"400 BAD_REQUEST\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/personal_info/roles/INVALID_ROLE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedErrorMessage));
    }
}