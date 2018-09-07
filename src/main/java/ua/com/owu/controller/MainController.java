package ua.com.owu.controller;


import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.models.User;
import ua.com.owu.service.MailService;
import ua.com.owu.service.UserService;
import ua.com.owu.utils.UserEditor;
import ua.com.owu.utils.UserValidator;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private Environment environment;


    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/ok")
    public String ok(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        model.addAttribute("username", name);
        System.out.println(name);
        return "ok";
    }

    @GetMapping("/books/input")
        public String bookInput(){
            return "" ;
        }


    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @Autowired
    private UserEditor userEditor;

    @Autowired
    UserValidator userValidator;

    @PostMapping("/save")
    public String save(User user,
                       BindingResult bindingResult,
                       Model model
            /*@RequestParam MultipartFile file*/
    ) throws IOException {
//        String username = user.getUsername();
//        String path = System.getProperty("user.dir")
//                + File.separator
//                + "src"
//                + File.separator
//                + "main"
//                + File.separator
//                + "resources"
//                + File.separator
//                + "static"
//                + File.separator;
//
//        File filename = new File(path
//                + user.getUsername()
//                + new Date().getTime()
//                + file.getOriginalFilename());
//        file.transferTo(filename);

        //user.setImage(filename.getName());


//        personValidator.validate(user,bindingResult);
//        if (bindingResult.hasErrors()) {
//
//            String errorMessage = "";
//            List<ObjectError> allErrors = bindingResult.getAllErrors();
//            for (ObjectError allError : allErrors) {
//                String code = allError.getCode();
//                errorMessage += " " + environment.getProperty(code);
//            }
//
//            model.addAttribute("error",errorMessage);
//            return "index";
//        }


        userEditor.setValue(user);
        userService.save(user);


        return "redirect:/login";
    }


    @GetMapping("/times")
    public String times() {
        return "times";
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

    ){
        try {
            mailService.sendSimpleMessage(email, subject, message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }


}
