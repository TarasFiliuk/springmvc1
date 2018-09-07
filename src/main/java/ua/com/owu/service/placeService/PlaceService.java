package ua.com.owu.service.placeService;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;

public interface PlaceService  {
    Place findByAdress (String adress);
    Place findByCity (String city);
    Place findByName (String name);
    Place findByEvents (Event event);
}
