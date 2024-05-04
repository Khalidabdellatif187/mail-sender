package com.cerebra.mailsender.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class MailGunEventsBasicAuthSecurity {

    @Value("${event.client.username}")
    private String userName;

    @Value("${event.client.password}")
    private String password;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(userName,password);
    }
}
