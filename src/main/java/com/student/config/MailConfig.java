package com.student.config;

import com.resend.Resend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailConfig {

    @Bean
    public Resend resendClient() {
        return new Resend(System.getenv("RESEND_API_KEY"));
    }
}