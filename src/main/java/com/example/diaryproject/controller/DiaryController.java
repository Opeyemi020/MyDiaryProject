package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.DeleteDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.LoginDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.services.DiaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sultan/")
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }
    @PostMapping("create_diary/")
    public ResponseEntity<CreateDiaryResponse> createDiary(@RequestBody CreateDiaryRequest request) {
        CreateDiaryResponse response = diaryService.createDiary(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login/")
    public ResponseEntity<LoginDiaryResponse> loginDiary(@RequestBody LoginDiaryRequest request) {
        try {
            LoginDiaryResponse loginResult = diaryService.loginDiary(request);
            return new ResponseEntity<>(loginResult, HttpStatus.OK);
        }
    }

    @PostMapping("/delete_diary/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable DeleteDiaryRequest diaryId){
            diaryService.deleteDiary(diaryId);
            return ResponseEntity.ok("Diary entry deleted successfully");

    }

}
