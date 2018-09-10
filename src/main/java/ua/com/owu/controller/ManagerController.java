package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.owu.models.User;
import ua.com.owu.service.userService.UserService;

@Controller
public class ManagerController {

    @Autowired
    UserService userService;


    @GetMapping("/create/manager_page")
    public String managerRegistration() {

        userService.save(new User("jjj","aaa"));

        return "managerRegistration";
    }

}
