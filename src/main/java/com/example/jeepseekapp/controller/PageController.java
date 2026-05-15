package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private RouteService routeService;

    private void nav(Model model, String page) {
        model.addAttribute("currentPage", page);
    }

    @GetMapping("/")
    public String home(Model model) { nav(model, "home"); return "home"; }

    @GetMapping("/routes")
    public String routes(Model model) {
        nav(model, "routes");
        model.addAttribute("routes", routeService.getAll());
        return "routes";
    }

    @GetMapping("/about")
    public String about(Model model) { nav(model, "about"); return "about"; }

    @GetMapping("/contact")
    public String contact(Model model) { nav(model, "contact"); return "contact"; }

    @GetMapping("/login")
    public String adminLogin(Model model) { nav(model, "login"); return "login"; }

    @GetMapping("/userlogin")
    public String userLogin(Model model) { nav(model, "userlogin"); return "userlogin"; }

    @GetMapping("/register")
    public String register(Model model) { nav(model, "register"); return "register"; }
}