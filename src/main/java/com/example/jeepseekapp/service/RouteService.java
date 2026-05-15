package com.example.jeepseekapp.service;

import com.example.jeepseekapp.model.Route;
import com.example.jeepseekapp.repository.RouteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Business logic for jeepney routes. Controllers MUST call this service
 * instead of touching RouteRepository directly.
 *
 * Seeds the DB with placeholder routes on first boot. Replace with real
 * data when GPS coordinates are ready.
 */
@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    public List<Route> getAll() {
        return repo.findAll();
    }

    public Route getByCode(String code) {
        return repo.findByCode(code);
    }

    @PostConstruct
    public void seed() {
        if (repo.count() > 0) return;
        repo.save(new Route("01K","Lahug — Colon","Lahug","Colon Street","JY Square, Salinas Drive, Fuente Osmeña, Colon","₱13.00","#e6194B","[[10.3361,123.9072],[10.3210,123.9020],[10.2956,123.9015]]"));
        repo.save(new Route("03B","Ayala — SM City","Ayala Center","SM City Cebu","Banilad, Talamban, North Reclamation Area","₱13.00","#3cb44b","[[10.3181,123.9047],[10.3290,123.9120],[10.3115,123.9180]]"));
        repo.save(new Route("04L","Lahug — Ayala","Lahug","Ayala Center","Capitol Site, Escario, Ayala Access Road","₱13.00","#4363d8","[[10.3361,123.9072],[10.3270,123.9050],[10.3181,123.9047]]"));
        repo.save(new Route("06B","Guadalupe — SM City","Guadalupe","SM City Cebu","V. Rama, Colon, Port Area, North Reclamation","₱13.00","#f58231","[[10.3050,123.8870],[10.2956,123.9015],[10.3115,123.9180]]"));
        repo.save(new Route("06H","Bulacao — Ayala","Bulacao","Ayala Center","Pardo, Tabunok, N. Bacalso, Fuente","₱13.00","#911eb4","[[10.2750,123.8500],[10.2900,123.8900],[10.3181,123.9047]]"));
        repo.save(new Route("12G","Mandaue — Carbon","Mandaue City","Carbon Market","A.C. Cortes, Mandaue Bridge, M.C. Briones, Colon","₱13.00","#42d4f4","[[10.3236,123.9223],[10.3050,123.9050],[10.2935,123.9020]]"));
        repo.save(new Route("12L","Consolacion — SM City","Consolacion","SM City Cebu","Mandaue, North Reclamation Area","₱15.00","#f032e6","[[10.3760,123.9540],[10.3300,123.9300],[10.3115,123.9180]]"));
        repo.save(new Route("13C","Banilad — Carbon","Banilad","Carbon Market","Talamban Road, Gorordo, Fuente, Colon","₱13.00","#bfef45","[[10.3450,123.9100],[10.3200,123.9050],[10.2935,123.9020]]"));
        repo.save(new Route("14D","Mambaling — IT Park","Mambaling","IT Park","Tres de Abril, Fuente, Archbishop Reyes","₱13.00","#469990","[[10.2870,123.8900],[10.3100,123.8990],[10.3300,123.9050]]"));
        repo.save(new Route("17B","Talisay — Colon","Talisay City","Colon Street","Tabunok, Bulacao, N. Bacalso, Colon","₱15.00","#9A6324","[[10.2447,123.8493],[10.2800,123.8800],[10.2956,123.9015]]"));
    }
}