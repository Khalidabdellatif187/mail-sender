package com.cerebra.mailsender.repository;

import com.cerebra.mailsender.enums.MailStatus;
import com.cerebra.mailsender.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MailRepository extends JpaRepository<Mail,Long> {



    @Modifying
    @Query("""
            update Mail m set m.eventDate= :eventDate where m.messageId= :messageId and m.createdDate= :createdDate
            """)
    void updateEventDateForMailEvent(@Param("eventDate") LocalDateTime eventDate,
                                     @Param("messageId")String messageId,
                                     @Param("createdDate") LocalDateTime createdDate);

}
