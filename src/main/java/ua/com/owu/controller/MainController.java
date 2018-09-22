package ua.com.owu.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.*;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.service.MailService;

import ua.com.owu.utils.AccountEditor;
import ua.com.owu.utils.TokenUtils;
import ua.com.owu.utils.UserValidator;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    UserValidator userValidator;

    @Autowired
    TokenUtils tokenUtils;
//    @PostMapping("/login")
//    public String login (Authentication authentication){
//        boolean isUser = false;
//        boolean isManager = false;
//        Collection<? extends GrantedAuthority> authorities
//                = authentication.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
//                isManager = true;
//                break;
//            }
//        }
//
//        if (isUser) {
//            return "index";
//        } else if (isManager) {
//            return "managerPage";
//        } else {
//            throw new IllegalStateException();
//        }
//    }
//    @GetMapping("/adminPage")
//    public String adminPage (){
//    return "adminT";
//    }

    @GetMapping("/managerPage")
    public String managerPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account byUsername = accountService.findByUsername(auth.getName());
        Role role = byUsername.getRole();
        String accountType = byUsername.getAccountType();
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        model.addAttribute("accType", accountType);
        System.out.println(name);
        return "managerPage";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Account byUsername = accountService.findByUsername(auth.getName());
        Role role = byUsername.getRole();
        String accountType = byUsername.getAccountType();
        model.addAttribute("username", name);
        model.addAttribute("role", role);
        model.addAttribute("accType", accountType);
        System.out.println(name);
        return "index";
    }

    //temporary methods to  create  admin
    @GetMapping("/createAdmin")
    public String createAdmin() {
        Admin admin = new Admin(Role.ROLE_ADMIN, "admin", "admin", "as@as");
        accountEditor.setValue(admin);
        accountService.save(admin);
        return "index";
    }


    //AdminController
    @GetMapping("/admin/page")
    String adminPage(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("adminName", name);


        List<Account> manager = accountService.findByAccountType("manager");
        Stream<Account> stream = manager.stream();
        List<Account> collect = stream.filter(account -> account.isAccountNonLocked() == false).collect(Collectors.toList());
        model.addAttribute("manager", collect);
        return "admin";
    }

    @GetMapping("/admin/active/manager/id/{id}")
    String confirm(
            @PathVariable int id
    ) {
        Account managerAccount = accountService.findbyId(id);
        managerAccount.setAccountNonLocked(true);
        accountService.save(managerAccount);
        return "redirect:/admin/page";
    }

    //USERcONTROLLER

    @PostMapping("/save")
    public String save(User user,
                       BindingResult bindingResult,
                       Model model
    ) throws IOException {
        String username = user.getUsername();
        userValidator.validate(user, bindingResult);
        user.setToken(tokenUtils.generateToken());
        if (bindingResult.hasErrors()) {
            String errorMessage = "";
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                String code = allError.getCode();
                errorMessage += " " + environment.getProperty(code);
            }
            model.addAttribute("error", errorMessage);
            return "index";
        }
        try {
            mailService.sendConfirmMessage(user.getEmail(), user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        accountEditor.setValue(user);
        accountService.save(user);
        return "redirect:/";
    }


    @GetMapping("/confirm/{token}")
    public String accontConfirm(@PathVariable String token) {
        Account byToken = accountService.findByToken(token);
        if (byToken != null) {
            byToken.setToken(null);
            byToken.setEnabled(true);
            accountService.save(byToken);
            System.out.println(byToken.getUsername() + " is approveed!");
            return "login";
        } else {
            System.out.println("there is  no tokens  like  that!");
            return "index";
        }
    }

    //MANAGERCONTROLLER

    @PostMapping("/save/manager")
    public String manager(Manager manager) {
        accountEditor.setValue(manager);
        manager.setRole(Role.ROLE_MANAGER);
        manager.setAccountNonLocked(false);
        accountService.save(manager);
        return "redirect:/";
    }

    @PostMapping("/save/admin")
    public String saveAdmin(Admin admin) {
        accountEditor.setValue(admin);
        admin.setRole(Role.ROLE_ADMIN);
        admin.setAccountNonLocked(true);
        accountService.save(admin);
        return "redirect:/";
    }


    @GetMapping("/places")
    public String places() {
        return "places";
    }

    //    @GetMapping("/create/manager_page")
//    public String managerRegistration() {
//
//
//        return "managerRegistration";
//    }
    @PostMapping("/admin/search")
    public String serchCustom(Model model){
    return null ;
    }




}

//for maincontroller

