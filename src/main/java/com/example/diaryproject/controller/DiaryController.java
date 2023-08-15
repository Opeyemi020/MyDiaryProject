package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.services.DiaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("sultan")
public class DiaryController {

    private DiaryService diaryService;

//    @PostMapping("/create-new-diary/")
//    public ResponseEntity<CreateDiaryResponse> createDiary(@RequestBody CreateDiaryRequest request){
//        return new ResponseEntity<>(diaryService.createDiary(request), HttpStatus.OK);
////        try {
////           return new ResponseEntity<>(request.getUsername(), HttpStatus.OK);
////
////        }catch (Exception e){
////            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
////        }
//    }
    @PostMapping("/create_diary")
    public CreateDiaryResponse createDiary(@RequestBody CreateDiaryRequest request){
        return diaryService.createDiary(request);
    }
    @PostMapping("/login/")
    public Object loginDiary(@RequestBody LoginDiaryRequest request) {
        try {
            return diaryService.loginDiary(request);
        } catch (DiaryDoesNotExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
