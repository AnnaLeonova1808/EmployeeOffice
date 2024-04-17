package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/create_event")
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }
}
