package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contact")
    public String submit(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String subject,
                         @RequestParam String message,
                         Model model) {
        contactService.save(name, email, subject, message);
        model.addAttribute("currentPage", "contact");
        model.addAttribute("submitted", true);
        return "contact";
    }
}