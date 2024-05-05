package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
public class VacancyControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void creteVacancyTest() throws Exception {

        VacancyCreateDto vacancyCreateDto = ExpectedData.returnVacancyCreateDto();

        String vacancyWrite = objectMapper.writeValueAsString(vacancyCreateDto);

        MvcResult createVacancyResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/vacancy/create_vacancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vacancyWrite))
                .andReturn();

        String jsonResult = createVacancyResult.getResponse().getContentAsString();
        VacancyAfterCreationDto vacancyAfterCreationDto = objectMapper.readValue(jsonResult, VacancyAfterCreationDto.class);

        Assertions.assertEquals(201, createVacancyResult.getResponse().getStatus());
        Assertions.assertNotNull(vacancyAfterCreationDto.getVacancyId());

        System.out.println(jsonResult);

    }

    @Test
    void deleteVacancyTestWithException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/vacancy/delete/61b02a7ee57c4321ba3473d59bfbddec"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(content().json("{\"type\":\"about:blank\",\"title\":\"Not Found\",\"status\":404," +
                        "\"detail\":\"No static resource vacancy/delete/61b02a7ee57c4321ba3473d59bfbddec.\",\"instance\":" +
                        "\"/vacancy/delete/61b02a7ee57c4321ba3473d59bfbddec\"}"))
                .andReturn();
    }
}