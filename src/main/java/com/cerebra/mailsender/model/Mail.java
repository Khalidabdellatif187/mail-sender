package com.cerebra.mailsender.model;


import com.cerebra.mailsender.enums.MailStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Map;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mail")
@Getter
@Setter
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String body;

    @Column
    private String subject;


    @Column
    private String sender;

    @Column
    private String recipient;

    @Column
    @Enumerated(EnumType.STRING)
    private MailStatus mailStatus = MailStatus.DRAFT;

    @CreatedDate
    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime sentDate;


    @Column
    private String messageId;

    @Column
    private LocalDateTime eventDate;

    @OneToMany(mappedBy = "mail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MailLink> mailLinks;


}
