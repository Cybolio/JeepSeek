package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.model.User;
import com.example.jeepseekapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserService users;

    public AuthController(UserService users) { this.users = users; }

    // ---------- Register ----------
    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam(name = "confirm", required = false) String confirm,
                           Model model, HttpSession session) {
        if (confirm != null && !password.equals(confirm)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("currentPage", "register");
            return "register";
        }
        try {
            User u = users.register(name, email, password);
            session.setAttribute("userId", u.getId());
            session.setAttribute("userName", u.getName());
            session.setAttribute("userRole", u.getRole().name());
            return "redirect:/profile";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("currentPage", "register");
            return "register";
        }
    }

    // ---------- User Login ----------
    @PostMapping("/userlogin")
    public String userLogin(@RequestParam String email,
                            @RequestParam String password,
                            Model model, HttpSession session) {
        Optional<User> opt = users.authenticate(email, password);
        if (opt.isEmpty()) {
            model.addAttribute("error", "Invalid email or password");
            model.addAttribute("currentPage", "userlogin");
            return "userlogin";
        }
        User u = opt.get();
        session.setAttribute("userId", u.getId());
        session.setAttribute("userName", u.getName());
        session.setAttribute("userRole", u.getRole().name());
        return "redirect:/profile";
    }

    // ---------- Admin Login (same auth, requires ADMIN role) ----------
    @PostMapping("/login")
    public String adminLogin(@RequestParam String email,
                             @RequestParam String password,
                             Model model, HttpSession session) {
        Optional<User> opt = users.authenticate(email, password)
                .filter(u -> u.getRole() == User.Role.ADMIN);
        if (opt.isEmpty()) {
            model.addAttribute("error", "Invalid admin credentials");
            model.addAttribute("currentPage", "login");
            return "login";
        }
        User u = opt.get();
        session.setAttribute("userId", u.getId());
        session.setAttribute("userName", u.getName());
        session.setAttribute("userRole", u.getRole().name());
        return "redirect:/admin/users";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/routes";
    }

    // ---------- Profile (rename + favorites) ----------
    @PostMapping("/profile/rename")
    public String rename(@RequestParam String name, HttpSession session) {
        Long uid = (Long) session.getAttribute("userId");
        if (uid == null) return "redirect:/userlogin";
        User u = users.rename(uid, name);
        session.setAttribute("userName", u.getName());
        return "redirect:/profile";
    }
}
