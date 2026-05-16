package com.example.jeepseekapp.controller;

import com.example.jeepseekapp.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FavoriteController {

    private final FavoriteService favs;

    public FavoriteController(FavoriteService favs) { this.favs = favs; }

    @PostMapping("/favorites/add")
    public String add(@RequestParam Long routeId, HttpSession session) {
        Long uid = (Long) session.getAttribute("userId");
        if (uid == null) return "redirect:/userlogin";
        favs.add(uid, routeId);
        return "redirect:/profile";
    }

    @PostMapping("/favorites/delete")
    public String delete(@RequestParam Long routeId, HttpSession session) {
        Long uid = (Long) session.getAttribute("userId");
        if (uid == null) return "redirect:/userlogin";
        favs.remove(uid, routeId);
        return "redirect:/profile";
    }
}
