package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.services.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("sultan")
public class DiaryController {

    private DiaryService diaryService;

    @PostMapping("/create-new-diary/")
    public Object createDiary(@RequestBody CreateDiaryRequest request){
        try {
            return diaryService.createDiary(request);
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/login/")
    public Object loginDiary(@RequestBody LoginDiaryRequest request){
        try {
            return diaryService.loginDiary(request);
        } catch (DiaryDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
//    @PostMapping("/deleteDiary/")

}
