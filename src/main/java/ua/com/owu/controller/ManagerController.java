package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.owu.models.*;
import ua.com.owu.service.accountService.AccountService;
import ua.com.owu.service.placeService.PlaceService;
import ua.com.owu.utils.AccountEditor;

@Controller
public class ManagerController {
    private final
    AccountService accountService;

    private final
    AccountEditor accountEditor;


    private final PlaceService placeService;

    @Autowired
    public ManagerController(AccountService accountService, AccountEditor accountEditor, PlaceService placeService) {
        this.accountService = accountService;
        this.accountEditor = accountEditor;
        this.placeService = placeService;
    }


    @PostMapping("/save/manager")
    public String manager(Manager manager) {
        accountEditor.setValue(manager);
        manager.setRole(Role.ROLE_MANAGER);
        manager.setAccountNonLocked(false);
        accountService.save(manager);
        return "redirect:/";
    }



    @GetMapping("/create/manager_page")
    public String managerRegistration() {


        return "managerRegistration";
    }


    @GetMapping("/manager-account")
    public String manPage(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken)) {
            Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Place byManagerId = placeService.findByManagerId(manager.getId());
            model.addAttribute("manager", manager);
            model.addAttribute("place", byManagerId);
            return "managerPage/managerPage";
        }
        else
            return "redirect:/";

    }

    @GetMapping("/manager-account/delete")
    public String deleteAccount(){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        accountService.deleteById(manager.getId());
    return "redirect:/";
    }

    @GetMapping("/manager-account/place/delete")
    public String deletePlace(){
        Manager manager = (Manager) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(manager);
        manager.setPlace(null);
        accountService.save(manager);
        return "redirect:/manager-account";
    }
}
