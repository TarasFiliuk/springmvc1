package ua.com.owu.service.placeService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Event;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;

import java.util.List;

public interface PlaceService  {
    Place findByAddress (String address);
    Place findByCity (String city);
    Place findByName (String name);
    Place findByEvents (Event event);
    Place findById (int id);
    void save(Place place);
    void deleteById(int id);
    void update(int id , Place place);
    List<Place> findAll();

}
