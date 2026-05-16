package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.service.FavoriteService;
import com.example.jeepseekapp.service.RouteService;
import com.example.jeepseekapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final RouteService routeService;
    private final FavoriteService favoriteService;
    private final UserService userService;

    public PageController(RouteService routeService, FavoriteService favoriteService, UserService userService) {
        this.routeService = routeService;
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    private void nav(Model model, String page, HttpSession session) {
        model.addAttribute("currentPage", page);
        model.addAttribute("sessionUserId", session.getAttribute("userId"));
        model.addAttribute("sessionUserName", session.getAttribute("userName"));
        model.addAttribute("sessionUserRole", session.getAttribute("userRole"));
    }

    @GetMapping("/")
    public String home() { return "redirect:/routes"; }

    @GetMapping("/routes")
    public String routes(Model model, HttpSession session) {
        nav(model, "routes", session);
        model.addAttribute("routes", routeService.getAll());
        return "routes";
    }

    @GetMapping("/about")
    public String about(Model model, HttpSession session) { nav(model, "about", session); return "about"; }

    @GetMapping("/contact")
    public String contact(Model model, HttpSession session) { nav(model, "contact", session); return "contact"; }

    @GetMapping("/login")
    public String adminLogin(Model model, HttpSession session) { nav(model, "login", session); return "login"; }

    @GetMapping("/userlogin")
    public String userLogin(Model model, HttpSession session) { nav(model, "userlogin", session); return "userlogin"; }

    @GetMapping("/register")
    public String register(Model model, HttpSession session) { nav(model, "register", session); return "register"; }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        Long uid = (Long) session.getAttribute("userId");
        if (uid == null) return "redirect:/userlogin";
        nav(model, "profile", session);
        model.addAttribute("user", userService.findById(uid).orElseThrow());
        model.addAttribute("favorites", favoriteService.listFor(uid));
        return "profile";
    }
}
