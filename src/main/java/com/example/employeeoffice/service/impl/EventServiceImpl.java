package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.EventAlreadyExistException;
import com.example.employeeoffice.exception.ListOfEventIsEmptyException;
import com.example.employeeoffice.repository.EventRepository;
import com.example.employeeoffice.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Event createEvent(Event event) {

        return eventRepository.saveAndFlush(event);
    }

    @Override
    public List<Event> showAllEvent() {
        List<Event> eventList = eventRepository.findAll();
        if (eventList.isEmpty()) throw new ListOfEventIsEmptyException(ErrorMessage.LIST_OF_EVENT_IS_EMPTY);
        return eventList;
    }

}
