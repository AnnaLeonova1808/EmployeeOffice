package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.repository.EventRepository;
import com.example.employeeoffice.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    public Event createEvent(Event event) {

        return eventRepository.saveAndFlush(event);
    }
}
