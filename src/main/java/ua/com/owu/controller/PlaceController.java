package ua.com.owu.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.Account;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.service.placeService.PlaceService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaceController {

    final
    PlaceService placeService;
    final AccountService accountService;

    @Autowired
    public PlaceController(PlaceService placeService, AccountService accountService) {
        this.placeService = placeService;
        this.accountService = accountService;
    }

    @PostMapping("manager-account/place")
    public String createPlace(Place place){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        placeService.save(place);
        manager.setPlace(place);
        accountService.save(manager);
        return "redirect:/manager-account";
    }

    @PostMapping("manager-account/place/update")
    public String updatePlace(Place place){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        placeService.update(manager, place);
        return "redirect:/manager-account";
    }


}
