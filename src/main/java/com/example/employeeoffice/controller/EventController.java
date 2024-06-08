package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.service.interfaces.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ADMIN')")
    @CreateEvent(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event) {

        return eventService.createEvent(event);

    }
}
