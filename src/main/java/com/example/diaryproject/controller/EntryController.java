package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.services.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("sultan")
public class EntryController {
    private EntryService entryService;

    @PostMapping("/create-new-entry/")
    public ResponseEntity<?>createEntry(@RequestBody CreateEntryRequest request) {
        try {
            return new ResponseEntity<>(request.getBody(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
