package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);

    List<Event> showAllEvent();
}
