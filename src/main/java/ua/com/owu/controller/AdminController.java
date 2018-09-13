package ua.com.owu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;
import ua.com.owu.service.AccountService.AccountService;

@Controller
public class AdminController {

    @Autowired
    AccountService accountService;

    @GetMapping("/admin/page")
    String adminPage(){
        return "admin";
    }


}
