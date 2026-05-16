package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.model.User;
import com.example.jeepseekapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService users;
    public AdminUserController(UserService users) { this.users = users; }

    private boolean notAdmin(HttpSession s) {
        return !"ADMIN".equals(s.getAttribute("userRole"));
    }

    @GetMapping
    public String list(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/login";
        model.addAttribute("users", users.findAll());
        model.addAttribute("currentPage", "admin");
        return "admin/users";
    }

    @GetMapping("/new")
    public String newForm(Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/login";
        model.addAttribute("currentPage", "admin");
        return "admin/user_form";
    }

    @PostMapping
    public String create(@RequestParam String name, @RequestParam String email,
                         @RequestParam String password, @RequestParam String role,
                         HttpSession session, Model model) {
        if (notAdmin(session)) return "redirect:/login";
        try {
            users.adminCreate(name, email, password, User.Role.valueOf(role));
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("currentPage", "admin");
            return "admin/user_form";
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, HttpSession session) {
        if (notAdmin(session)) return "redirect:/login";
        model.addAttribute("u", users.findById(id).orElseThrow());
        model.addAttribute("currentPage", "admin");
        return "admin/user_edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name, @RequestParam String email,
                         @RequestParam String role,
                         @RequestParam(required = false) String password,
                         HttpSession session) {
        if (notAdmin(session)) return "redirect:/login";
        users.adminUpdate(id, name, email, User.Role.valueOf(role), password);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (notAdmin(session)) return "redirect:/login";
        users.delete(id);
        return "redirect:/admin/users";
    }
}
