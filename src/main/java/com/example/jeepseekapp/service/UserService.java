package com.example.jeepseekapp.service;

import com.example.jeepseekapp.model.User;
import com.example.jeepseekapp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository repo) { this.repo = repo; }

    @PostConstruct
    public void seed() {
        if (!repo.existsByEmail("admin@jeepseek.local")) {
            repo.save(new User("Admin", "admin@jeepseek.local",
                    encoder.encode("admin123"), User.Role.ADMIN));
        }
    }

    public User register(String name, String email, String rawPassword) {
        if (repo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        return repo.save(new User(name, email, encoder.encode(rawPassword), User.Role.USER));
    }

    public Optional<User> authenticate(String email, String rawPassword) {
        return repo.findByEmail(email)
                .filter(u -> encoder.matches(rawPassword, u.getPasswordHash()));
    }

    public User rename(Long userId, String newName) {
        User u = repo.findById(userId).orElseThrow();
        u.setName(newName);
        return repo.save(u);
    }

    // ----- Admin CRUD -----
    public List<User> findAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }

    public User adminCreate(String name, String email, String rawPassword, User.Role role) {
        if (repo.existsByEmail(email)) throw new IllegalArgumentException("Email already registered");
        return repo.save(new User(name, email, encoder.encode(rawPassword), role));
    }

    public User adminUpdate(Long id, String name, String email, User.Role role, String newPasswordOrNull) {
        User u = repo.findById(id).orElseThrow();
        u.setName(name);
        u.setEmail(email);
        u.setRole(role);
        if (newPasswordOrNull != null && !newPasswordOrNull.isBlank()) {
            u.setPasswordHash(encoder.encode(newPasswordOrNull));
        }
        return repo.save(u);
    }

    public void delete(Long id) { repo.deleteById(id); }
}
