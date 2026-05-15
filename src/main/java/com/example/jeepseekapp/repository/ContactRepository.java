package com.example.jeepseekapp.repository;

import com.example.jeepseekapp.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
}