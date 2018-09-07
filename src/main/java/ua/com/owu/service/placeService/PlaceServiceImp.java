package ua.com.owu.service.placeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.PlaceDAO;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;
import ua.com.owu.service.placeService.PlaceService;

@Service
@Transactional
public class PlaceServiceImp implements PlaceService {
    @Autowired
    PlaceDAO placeDAO;
    @Override
    public Place findByAdress(String adress) {
        return placeDAO.findByAdress(adress);
    }

    @Override
    public Place findByCity(String city) {
        return placeDAO.findByCity(city);
    }

    @Override
    public Place findByName(String name) {
        return placeDAO.findByName(name);
    }

    @Override
    public Place findByEvents(Event event) {
        return placeDAO.findByEvents(event);
    }
}
