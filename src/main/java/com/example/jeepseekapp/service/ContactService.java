package com.example.jeepseekapp.service;

import com.example.jeepseekapp.model.ContactMessage;
import com.example.jeepseekapp.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository repo;

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    public ContactMessage save(String name, String email, String subject, String message) {
        ContactMessage m = new ContactMessage();
        m.setName(name);
        m.setEmail(email);
        m.setSubject(subject);
        m.setMessage(message);
        return repo.save(m);
    }
}