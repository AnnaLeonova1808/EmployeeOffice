package com.example.employeeoffice;

import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.enums.Position;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
@ComponentScan(basePackages = {"com.example.employeeoffice"})
@SpringBootTest
@AutoConfigureMockMvc
@SpringJUnitConfig
@Component
@Sql("/db/dataTest.sql")
@Sql("/db/schemaTest.sql")

public class VacancyControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void creteVacancyTest() throws Exception {
        VacancyCreateDto vacancyDto = new VacancyCreateDto(Position.PROGRAMMER, "Programmer P D",
                "mmm", "1236", "IT");
    }
}