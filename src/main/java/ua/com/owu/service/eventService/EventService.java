package ua.com.owu.service.eventService;


import org.joda.time.DateTime;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;

import java.util.List;


public interface EventService {
    Event save(Event event);
    Event findById (int id);
    Event update(int id, Event newEvent);
    List<Event> findByPlaceId(int id);
    List<Event> findByPlace(Place place);
    List<Event> findByDate(DateTime dateTime);
    List<Event> findAll();
    void deleteById(int id);

}
