package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.WorkScheduleRepository;
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

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/drop_tables_test.sql")
@Sql("/db/schemaTest.sql")
@Sql("/db/dataTest.sql")
@WithMockUser(username = "michael", password = "111", roles = "ADMIN")
class WorkScheduleControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    WorkScheduleRepository workScheduleRepository;

    @Test
    void showByName() throws Exception{
        WorkSchedule expectedWorkSchedule = ExpectedData.returnWorkScheduleByName();

        when(workScheduleRepository.findBySchedName(WorkScheduleName.SHIFT_WORK)).thenReturn(expectedWorkSchedule);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/work_schedules/showByName/SHIFT_WORK", expectedWorkSchedule.getSchedName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualWorkScheduleJSON = mvcResult.getResponse().getContentAsString();
        WorkSchedule actualWorkSchedule = objectMapper.readValue(actualWorkScheduleJSON, WorkSchedule.class);

        System.out.println("Expected workSchedule: " + expectedWorkSchedule);
        System.out.println("Actual workSchedule: " + actualWorkSchedule);

        Assertions.assertEquals(expectedWorkSchedule, actualWorkSchedule);
    }
    @Test
    void showWorkScheduleByNameTestWithException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/work_schedules/showByName/SIX_DAY_SHIFT_09_16"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"message\":\"WORK_SCHEDULE_NOT_FOUND\",\"errorCode\":\"404 NOT_FOUND\"}"));


    }

    @Test
    void showAllWorkSchedulePositiveTest() throws Exception {

        Set<WorkSchedule> departmentSet = ExpectedData.returnAllWorkSchedules();
        List<WorkSchedule> departmentList = List.copyOf(departmentSet);

        given(workScheduleRepository.findAll()).willReturn(departmentList);

        Set<String> expectedWorkScheduleNames = departmentSet.stream()
                .map(WorkSchedule::getSchedName)
                .map(WorkScheduleName::name)
                .collect(Collectors.toSet());

        Set<String> actualWorkScheduleNames = showAll();
        assertEquals(expectedWorkScheduleNames, actualWorkScheduleNames);
    }
    @Test
    void showAllWorkScheduleNegativeTest() throws Exception {
        given(workScheduleRepository.findAll()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/work_schedules/showAllWorkSchedule"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(ErrorMessage.LIST_OF_WORK_SCHEDULE_IS_EMPTY))
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()));
    }

    Set<String> showAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/work_schedules/showAllWorkSchedule"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String workScheduleListJSON = mvcResult.getResponse().getContentAsString();
        return objectMapper.readValue(workScheduleListJSON, new TypeReference<Set<String>>() {});
    }
}