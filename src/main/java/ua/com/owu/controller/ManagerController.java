package ua.com.owu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/create/manager_page")
    public String managerRegistration() {
        return "managerRegistration";
    }

}
