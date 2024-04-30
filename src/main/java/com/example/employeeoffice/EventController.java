package com.example.employeeoffice;
import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.service.interfaces.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @CreateEvent(path = "/create_event")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }


}
