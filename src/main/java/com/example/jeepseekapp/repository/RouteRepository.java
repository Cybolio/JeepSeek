package com.example.jeepseekapp.repository;

import com.example.jeepseekapp.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA gives you findAll/save/findById/etc. for free.
 * Add custom finders here as needed (e.g. findByCode).
 */
public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByCode(String code);
}