package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.Event;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;
import ua.com.owu.service.accountService.AccountService;
import ua.com.owu.service.eventService.EventService;
import ua.com.owu.service.placeService.PlaceService;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

@RestController
public class EventRestController {

    private final EventService eventService;
    private final PlaceService placeService;
    private final AccountService accountService;

    @Autowired
    public EventRestController(EventService eventService, PlaceService placeService, AccountService accountService) {
        this.eventService = eventService;
        this.placeService = placeService;
        this.accountService = accountService;
    }

    @GetMapping("/events")
    public List<Event> getAllEvents(){
    return eventService.findAll();
    }


    @GetMapping( value = "manager-account/events",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<Event> getAllEventForCurrentPlace(){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Place place = placeService.findByManagerId(manager.getId());
        return eventService.findByPlace(place);
    }

    @PostMapping( value = "manager-account/events",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Event createEvent(@RequestBody Event event){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Place place = placeService.findByManagerId(manager.getId());
        event.setPlace(place);
        System.out.println(event);
        return eventService.save(event);
    }

    @DeleteMapping(value = "manager-account/events/{id}")
    public String deleteEvent(@PathVariable int id){
        eventService.deleteById(id);
        return "Successfully deleted!";
    }

    @PutMapping(value = "manager-account/events/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Event updateEvent(@RequestBody Event event, @PathVariable int id){
        return eventService.update(id, event);
    }




}
