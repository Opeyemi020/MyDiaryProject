package com.example.diaryproject.controller;

import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.services.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("sultan")
public class EntryController {
    private EntryService entryService;

    @PostMapping("/create-new-entry/")
    public Object createEntry(@RequestBody CreateEntryRequest request) {
        try {
            return entryService.createEntry(request);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
