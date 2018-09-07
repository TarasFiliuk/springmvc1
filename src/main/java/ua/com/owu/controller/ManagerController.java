package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.com.owu.service.MailService;
import ua.com.owu.service.managerService.ManagerService;

@Controller
public class ManagerController {

    @Autowired
    private MailService mailService;

    @Autowired
    private ManagerService managerService;
}
