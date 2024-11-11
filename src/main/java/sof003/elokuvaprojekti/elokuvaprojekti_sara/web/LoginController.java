package sof003.elokuvaprojekti.elokuvaprojekti_sara.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Thymeleaf-templaten nimi
    }
}