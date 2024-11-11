package com.anasdev.emailsendingAPI.repository;

import com.anasdev.emailsendingAPI.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
