package com.example.diaryproject.services.mailService;

import com.example.diaryproject.dtos.requests.SendMailRequest;

public interface MailService {
    void sendMail(SendMailRequest request);
}
