package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.DeleteDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.services.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> loginDiary(@RequestBody LoginDiaryRequest request) {
        try {
            Object loginResult = diaryService.loginDiary(request);
            return new ResponseEntity<>(loginResult, HttpStatus.OK);
        } catch (DiaryDoesNotExistException e) {
            return new ResponseEntity<>("Diary does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete_diary/")
    public ResponseEntity<?> deleteDiary(@Pa){
        try{
            diaryService.deleteDiary(request.getDiaryId());
            return ResponseEntity.ok("Diary entry deleted successfully");
        }
        catch (DiaryDoesNotExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}