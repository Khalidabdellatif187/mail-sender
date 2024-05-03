package com.cerebra.mailsender.repository;

import com.cerebra.mailsender.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<Mail,Long> {

}
