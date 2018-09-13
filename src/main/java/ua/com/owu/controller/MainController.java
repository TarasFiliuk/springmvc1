package ua.com.owu.controller;


import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.Account;
import ua.com.owu.models.Admin;
import ua.com.owu.models.Role;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.service.MailService;

import ua.com.owu.utils.AccountEditor;

import javax.mail.MessagingException;

@Controller
public class MainController {


    @Autowired
    private Environment environment;

    @Autowired
    private MailService mailService;


    @Autowired
    AccountService accountService;

    @Autowired
    AccountEditor accountEditor;

    @GetMapping("/")
    public String index() {


        return "index";
    }

    @GetMapping("/createAdmin")
    public String createAdmin() {
        Admin admin = new Admin(Role.ROLE_ADMIN, "admin", "admin", "as@as");
        accountEditor.setValue(admin);
        accountService.save(admin);
        return "index";
    }

    @PostMapping("/ok")
    public String ok(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username{
        Account byUsername = accountService.findByUsername(auth.getName());
        Role role = byUsername.getRole();
        String accountType = byUsername.getAccountType();
        model.addAttribute("username", name);
        model.addAttribute("role",role);
        model.addAttribute("accType",accountType);
            System.out.println(name);

        return "ok";
    }

    @GetMapping("/books/input")
    public String bookInput() {
        return "";
    }


    @GetMapping("/times")
    public String times() {
        return "index";
    }

    @PostMapping("/times")
    public String timesSave(
            @RequestParam String date, @RequestParam String start, @RequestParam String end
    ) {


        DateTime startDay = DateTime.parse(date);
        DateTime endDay = startDay.plusHours(24);
        DateTime startTime = startDay.plusHours(Integer.parseInt(start));
        DateTime endTime = startDay.plusHours(Integer.parseInt(end));
        Interval interval = new Interval(startTime, endTime);
        Interval interval1 = new Interval(startDay, endDay);

        System.out.println(startDay);
        System.out.println(endDay);
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(interval1.gap(interval));
        return "redirect:/times";
    }

    @PostMapping("/sentMail")
    public String sentMail(
            @RequestParam String email,
            @RequestParam String subject,
            @RequestParam String message

    ) {
        try {
            mailService.sendSimpleMessage(email, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }




}
