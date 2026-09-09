package com.student.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private Resend resend;

    public void sendOtpEmail(String email, String otp) {

        try {
            CreateEmailOptions params = CreateEmailOptions.builder()
                    .from("onboarding@resend.dev")
                    .to(email)
                    .subject("Password Reset OTP")
                    .html(
                            "<h2>Password Reset OTP</h2>" +
                            "<p>Your OTP for password reset is: <strong>" + otp + "</strong></p>" +
                            "<p>This OTP is valid for 5 minutes.</p>"
                    )
                    .build();

            resend.emails().send(params);

        } catch (ResendException e) {
            throw new RuntimeException("Email sending failed", e);
        }
    }
}