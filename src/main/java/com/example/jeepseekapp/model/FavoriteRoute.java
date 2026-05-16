package com.example.jeepseekapp.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "favorite_routes",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "route_id"}))
public class FavoriteRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "route_id")
    private Route route;

    @Column(name = "saved_at", nullable = false, updatable = false)
    private Instant savedAt = Instant.now();

    public FavoriteRoute() {}
    public FavoriteRoute(User user, Route route) { this.user = user; this.route = route; }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Route getRoute() { return route; }
    public Instant getSavedAt() { return savedAt; }
    public void setUser(User user) { this.user = user; }
    public void setRoute(Route route) { this.route = route; }
}
