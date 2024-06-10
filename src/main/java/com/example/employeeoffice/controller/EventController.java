package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public Event createEvent(@RequestBody Event event) {

        return eventService.createEvent(event);

    }
}
