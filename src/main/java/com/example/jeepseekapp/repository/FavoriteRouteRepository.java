package com.example.jeepseekapp.repository;

import com.example.jeepseekapp.model.FavoriteRoute;
import com.example.jeepseekapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRouteRepository extends JpaRepository<FavoriteRoute, Long> {
    List<FavoriteRoute> findByUserOrderBySavedAtDesc(User user);
    Optional<FavoriteRoute> findByUserIdAndRouteId(Long userId, Long routeId);
    boolean existsByUserIdAndRouteId(Long userId, Long routeId);
    void deleteByUserIdAndRouteId(Long userId, Long routeId);
}
