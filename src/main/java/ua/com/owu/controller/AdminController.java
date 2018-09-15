package ua.com.owu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;
import ua.com.owu.service.AccountService.AccountService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class AdminController {

    @Autowired
    AccountService accountService;

    @GetMapping("/admin/page")
    String adminPage(Model model){

        List<Account> manager = accountService.findByAccountType("manager");
        Stream<Account> stream = manager.stream();
        List<Account> isLocked = stream.filter(account -> !account.isAccountNonLocked()).collect(Collectors.toList());
        model.addAttribute("manager",isLocked);

        return "adminT";
    }

    @GetMapping("/admin/active/manager/id/{id}")
    String confirm(
            @PathVariable int id
    ){
        Account managerAccount = accountService.findbyId(id);
        managerAccount.setAccountNonLocked(true);
        return "redirect:/admin/page";
    }

}
