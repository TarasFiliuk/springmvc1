package ua.com.owu.service.eventService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.models.Event;


public interface EventService {
    Event findByEventId (int id);
}
