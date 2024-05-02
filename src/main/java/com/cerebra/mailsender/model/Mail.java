package com.cerebra.mailsender.model;


import jakarta.persistence.*;

@Entity
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


}
