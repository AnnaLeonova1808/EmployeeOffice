package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.entity.Event;
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

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
class EventControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void creteEventTest() throws Exception {
        Event event = ExpectedData.returnEventCreate();
        String eventWrite = objectMapper.writeValueAsString(event);

        MvcResult createEventResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/event/create_event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventWrite))
                .andReturn();

        String jsonResult = createEventResult.getResponse().getContentAsString();
        Assertions.assertEquals(201, createEventResult.getResponse().getStatus());
        System.out.println(jsonResult);
    }

}