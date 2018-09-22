package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.owu.models.Account;
import ua.com.owu.models.Manager;
import ua.com.owu.models.User;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.utils.AccountEditor;

@Controller
public class ManagerController {
    @Autowired
    AccountService accountService;

    @Autowired
    AccountEditor accountEditor;


 @PostMapping("/save/manager")
 public String manager(Manager manager){
     accountEditor.setValue(manager);
     accountService.save(manager);

 return "redirect:/";
 }


    @GetMapping("/create/manager_page")
    public String managerRegistration() {


        return "managerRegistration";
    }


    @GetMapping("/manager-account")
    public String manPage(Model model){

        return "managerPage/managerPage";
    }
}
