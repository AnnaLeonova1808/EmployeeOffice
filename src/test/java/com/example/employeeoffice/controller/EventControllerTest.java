package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.enums.EventType;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.EventRepository;
import com.example.employeeoffice.utils.ExpectedData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
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
class EventControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EventRepository eventRepository;

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

    @Test
    void showAllEventPositiveTest() throws Exception {

        Set<Event> eventSet = ExpectedData.returnAllEvents();
        List<Event> eventList = new ArrayList<>(eventSet);

        given(eventRepository.findAll()).willReturn(eventList);

        MvcResult mvcResult = mockMvc.perform(get("/event/showAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String eventListJSON = mvcResult.getResponse().getContentAsString();
        List<Event> actualEventList = objectMapper.readValue(eventListJSON, new TypeReference<List<Event>>() {
        });

        Set<String> expectedEventTypes = eventSet.stream()
                .map(Event::getEvType)
                .map(EventType::name)
                .collect(Collectors.toSet());

        Set<String> actualEventTypes = actualEventList.stream()
                .map(Event::getEvType)
                .map(EventType::name)
                .collect(Collectors.toSet());

        assertEquals(expectedEventTypes, actualEventTypes);
    }
    @Test
    void showAllEventNegativeTest() throws Exception {
        given(eventRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/event/showAll"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_EVENT_IS_EMPTY))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

}