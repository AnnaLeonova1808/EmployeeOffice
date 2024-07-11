package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.annotation.ShowAllEvent;
import com.example.employeeoffice.annotation.ShowWorkScheduleByName;
import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.service.interfaces.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing events.
 */
@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    /**
     * Creates a new event.
     *
     * @param event the event to create
     * @return the created event
     */
    @CreateEvent(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@Valid @RequestBody Event event) {

        return eventService.createEvent(event);
    }

    /**
     * Retrieves all events.
     *
     * @return a list of all events
     */
    @ShowAllEvent(path = "/showAll")
    public List<Event> showAllEvent() {

        return eventService.showAllEvent();
    }
}
