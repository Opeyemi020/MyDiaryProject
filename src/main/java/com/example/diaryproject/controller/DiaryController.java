package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.services.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DiaryController {

    private DiaryService diaryService;

    @PostMapping("")
    public Object createDiary(@RequestBody CreateDiaryRequest request){
        try {
            return diaryService.createDiary(request);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("")
    public Object loginDiary(@RequestBody LoginDiaryRequest request){
        try {
            return diaryService.loginDiary(request);
        } catch (DiaryDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return e.getMessage();
        }


    }
}
