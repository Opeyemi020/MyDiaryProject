package com.example.diaryproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
@Component
public class BeanConfig {
    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.protocol}")
    private String mailProtocol;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(mailHost);
        javaMailSender.setPort(mailPort);
        javaMailSender.setProtocol(mailProtocol);
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.ssl.enable", "true");
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setPassword(mailPassword);
        javaMailSender.setUsername(mailUsername);
        return javaMailSender;
    }
}
