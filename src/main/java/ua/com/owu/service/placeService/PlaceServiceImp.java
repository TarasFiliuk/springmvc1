package ua.com.owu.service.placeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.PlaceDAO;
import ua.com.owu.models.Event;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;
import ua.com.owu.service.placeService.PlaceService;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImp implements PlaceService {
    @Autowired
    PlaceDAO placeDAO;

    @Override
    public Place findByAddress(String address) {
        return placeDAO.findByAddress(address);
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

    @Override
    @Transactional
    public Place findById(int id) {
        return placeDAO.findOne(id);
    }

    @Override
    @Transactional
    public void save(Place place) {
        placeDAO.save(place);
    }

    @Override
    public void deleteById(int id) {
        placeDAO.delete(id);
    }

    @Override
    public void update(int id, Place place) {
        place.setPlaceId(placeDAO.findOne(id).getPlaceId());
        placeDAO.save(place);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<Place> findAll() {
        return placeDAO.findAll();
    }

}
