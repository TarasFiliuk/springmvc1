package ua.com.owu.service.eventService;

import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.EventDAO;
import ua.com.owu.dao.PlaceDAO;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;

import java.util.List;

@Transactional
@Service
public class EventServiceImpl implements EventService {


    final
    EventDAO  eventDAO;

    final PlaceDAO placeDAO;

    @Autowired
    public EventServiceImpl(EventDAO eventDAO, PlaceDAO placeDAO) {
        this.eventDAO = eventDAO;
        this.placeDAO = placeDAO;
    }

    @Override
    public Event save(Event event) {
        return eventDAO.save(event);
    }

    @Override
    public Event findById(int id) {
        return eventDAO.findOne(id);
    }

    @Override
    public void deleteById(int id) {
        eventDAO.delete(id);
    }

    @Override
    public Event update(int id, Event newEvent) {
        Event eventDAOOne = eventDAO.findOne(id);
        BeanUtils.copyProperties(newEvent, eventDAOOne);
        return eventDAO.save(eventDAOOne);
    }

    @Override
    public List<Event> findByPlaceId(int id) {
        return  eventDAO.findByPlace(placeDAO.findOne(id));
    }

    @Override
    public List<Event> findByPlace(Place place) {
        return eventDAO.findByPlace(place);
    }

    @Override
    public List<Event> findByDate(DateTime dateTime) {
        return eventDAO.findByDate(dateTime);
    }

    @Override
    public List<Event> findAll() {
        return eventDAO.findAll();
    }
}
