package ua.com.owu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.*;
import ua.com.owu.service.accountService.AccountService;
import ua.com.owu.service.mailService.MailService;

import ua.com.owu.utils.AccountEditor;
import ua.com.owu.utils.TokenUtils;
import ua.com.owu.utils.AccountValidator;

import javax.mail.MessagingException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class MainController {


    private final Environment environment;

    private final MailService mailService;

    private final
    AccountService accountService;

    private final
    AccountEditor accountEditor;

    private final
    AccountValidator accountValidator;

    private final
    TokenUtils tokenUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MainController(Environment environment, MailService mailService, AccountService accountService, AccountEditor accountEditor, AccountValidator accountValidator, TokenUtils tokenUtils, PasswordEncoder passwordEncoder) {
        this.environment = environment;
        this.mailService = mailService;
        this.accountService = accountService;
        this.accountEditor = accountEditor;
        this.accountValidator = accountValidator;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = passwordEncoder;
    }
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
        List<Account> collect = stream.filter(account -> !account.isAccountNonLocked()).collect(Collectors.toList());
        model.addAttribute("manager", collect);
        return "admin";
    }

    @GetMapping("/admin/active/manager/id/{id}")
    String confirm(
            @PathVariable int id
    ) {
        Account managerAccount = accountService.findById(id);
        managerAccount.setAccountNonLocked(true);
        accountService.save(managerAccount);
        return "redirect:/admin/page";
    }

    //USERcONTROLLER

    @PostMapping("/save")
    public String save(User user,
                       BindingResult bindingResult,
                       Model model
    ){
        accountValidator.validate(user, bindingResult);
        user.setToken(tokenUtils.generateToken());
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                String code = allError.getCode();
                errorMessage.append(" ").append(environment.getProperty(code));
            }
            model.addAttribute("error", errorMessage.toString());
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
    public String accountConfirm(@PathVariable String token) {
        Account byToken = accountService.findByToken(token);
        if (byToken != null) {
            byToken.setToken(null);
            byToken.setEnabled(true);
            accountService.save(byToken);
            System.out.println(byToken.getUsername() + " is approved!");
            return "login";
        } else {
            System.out.println("there is  no tokens  like  that!");
            return "index";
        }
    }
 @GetMapping("/confirm/{email}/{token}")
    public String emailConfirm(@PathVariable String token, @PathVariable String email) {
        Account byToken = accountService.findByToken(token);
         System.out.println(byToken);
        if (byToken != null) {
            byToken.setToken(null);
            accountService.updateEmail(byToken.getId(), email);
            return "redirect:/";
        } else {
            System.out.println("there is  no tokens  like  that!");
            return "index";
        }
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

    @PostMapping("/admin/search")
    public String searchCustom(Model model){
    return null ;
    }




}

//for maincontroller

