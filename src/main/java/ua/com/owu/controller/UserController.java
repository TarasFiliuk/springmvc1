package ua.com.owu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.com.owu.models.Account;
import ua.com.owu.models.Role;
import ua.com.owu.models.User;
import ua.com.owu.service.MailService;
import ua.com.owu.service.AccountService.AccountService;
import ua.com.owu.utils.UserEditor;
import ua.com.owu.utils.UserValidator;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserEditor userEditor;

    @Autowired
    UserValidator userValidator;
    @Autowired
    private Environment environment;


    @Autowired
    private MailService mailService;


    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }





    @PostMapping("/save")
    public String save(User user,
                       BindingResult bindingResult,
                       Model model
    ) throws IOException {
        String username = user.getUsername();
        userValidator.validate(user,bindingResult);

        if (bindingResult.hasErrors()) {

            String errorMessage = "";
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError allError : allErrors) {
                String code = allError.getCode();
                errorMessage += " " + environment.getProperty(code);
            }

            model.addAttribute("error" , errorMessage);
            return "index";
        }


        try {
            mailService.sendConfirmMessage(user.getEmail(), user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        userEditor.setValue(user);
        accountService.save(user);


        return "redirect:/login";
    }
    @GetMapping("/userList")
    public String userList(Model model){
        List<Account> all = accountService.findByRole(Role.ROLE_USER);
        model.addAttribute("users", all);
        return "userList";
    }

}
