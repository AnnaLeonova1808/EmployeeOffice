package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacanсy;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        assertEquals(201, createVacancyResult.getResponse().getStatus());
        Assertions.assertNotNull(vacancyAfterCreationDto.getVacancyId());

        System.out.println(jsonResult);

    }

    @Test
    void deleteVacancyTestPositive() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/vacancy/delete_vacancy/51b02a7e-e57c-4321-ba34-73d59bfbddec"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Vacancy with this ID was deleted SUCCESSFULLY"))
                .andReturn().getResponse().getStatus();
}


    @Test
    void deleteVacancyTestWithException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/vacancy/delete_vacancy/61b02a7e-e57c-4321-ba34-73d59bfbddec"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"VACANCY_NOT_EXIST\",\"errorCode\":\"NOT_FOUND\"}"))
                .andReturn();
    }


}