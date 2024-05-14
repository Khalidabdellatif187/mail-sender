package com.cerebra.mailsender.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mail_link")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String url;

    @Column(nullable = false)
    private Integer clickCount = 0;

    @ManyToOne
    @JoinColumn(name = "mail_id")
    private Mail mail;

}
