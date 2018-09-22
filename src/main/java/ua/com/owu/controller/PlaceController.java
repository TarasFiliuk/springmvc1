package ua.com.owu.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.Account;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.service.placeService.PlaceService;

import java.util.List;

@RestController
public class PlaceController {

    final
    PlaceService placeService;
    final AccountService accountService;

    @Autowired
    public PlaceController(PlaceService placeService, AccountService accountService) {
        this.placeService = placeService;
        this.accountService = accountService;
    }

    @PostMapping("/places")
    public String createPlace(@RequestBody Place place){
        Hibernate.initialize(place.getManagers());
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken) ){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (manager.getAccountType().equals("manager")) {
                manager.setPlace(place);
                place.addManager(manager);
                accountService.save(manager);
            }}
            else {
            Manager managerOwn = (Manager) accountService.findbyId(1);
            managerOwn.setPlace(place);
                accountService.save(managerOwn);
                place.addManager(managerOwn);
            }
        System.out.println(place);
        placeService.save(place);
        return "Done!";
    }

    @GetMapping("/places")
    public List<Place> getPlaces(){
        return placeService.findAll();
    }

    @GetMapping("/places/{id}")
    public Place getOne(@PathVariable int id){
        return placeService.findById(id);
    }

}
