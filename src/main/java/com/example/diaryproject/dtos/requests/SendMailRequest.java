package com.example.diaryproject.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailRequest {
    private String to;
    private String from;
    private String subject;
    private String text;
}
