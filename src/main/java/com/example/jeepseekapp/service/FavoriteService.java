package com.example.jeepseekapp.service;

import com.example.jeepseekapp.model.FavoriteRoute;
import com.example.jeepseekapp.model.Route;
import com.example.jeepseekapp.model.User;
import com.example.jeepseekapp.repository.FavoriteRouteRepository;
import com.example.jeepseekapp.repository.RouteRepository;
import com.example.jeepseekapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteService {

    private final FavoriteRouteRepository favRepo;
    private final UserRepository userRepo;
    private final RouteRepository routeRepo;

    public FavoriteService(FavoriteRouteRepository favRepo, UserRepository userRepo, RouteRepository routeRepo) {
        this.favRepo = favRepo; this.userRepo = userRepo; this.routeRepo = routeRepo;
    }

    public List<FavoriteRoute> listFor(Long userId) {
        User u = userRepo.findById(userId).orElseThrow();
        return favRepo.findByUserOrderBySavedAtDesc(u);
    }

    public void add(Long userId, Long routeId) {
        if (favRepo.existsByUserIdAndRouteId(userId, routeId)) return;
        User u = userRepo.findById(userId).orElseThrow();
        Route r = routeRepo.findById(routeId).orElseThrow();
        favRepo.save(new FavoriteRoute(u, r));
    }

    @Transactional
    public void remove(Long userId, Long routeId) {
        favRepo.deleteByUserIdAndRouteId(userId, routeId);
    }
}
