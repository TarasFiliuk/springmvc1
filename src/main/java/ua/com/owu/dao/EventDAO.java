package ua.com.owu.dao;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;

import java.util.Date;
import java.util.List;

@Repository
public interface EventDAO extends JpaRepository<Event,Integer> {
    List<Event> findByPlace(Place place);
    List<Event> findByDate(DateTime dateTime);
}
