package co.edu.uptc.temufacebook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/persons";
    }
    
    @GetMapping("/home")
    public String homePage() {
        return "redirect:/persons";
    }
}