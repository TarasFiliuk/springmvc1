package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.owu.models.User;

@Controller
public class ManagerController {



    @GetMapping("/create/manager_page")
    public String managerRegistration() {


        return "managerRegistration";
    }

}
