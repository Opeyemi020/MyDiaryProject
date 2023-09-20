package com.example.diaryproject.services.mailService;

import com.example.diaryproject.dtos.requests.SendMailRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private JavaMailSender mailSender;
    @Override
    public void sendMail(SendMailRequest request) {
        SimpleMailMessage mailMessage= new SimpleMailMessage();
        mailMessage.setFrom(request.getFrom());
        mailMessage.setTo(request.getTo());
        mailMessage.setText(request.getText());
        mailMessage.setFrom(request.getFrom());
        mailSender.send(mailMessage);
    }
}
