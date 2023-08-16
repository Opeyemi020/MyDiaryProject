package com.example.diaryproject.utils;

import com.example.diaryproject.Data.models.Diary;
import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.models.User;
import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.dtos.requests.DeleteDiaryRequest;
import com.example.diaryproject.dtos.requests.RegisterUserRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.CreateEntryResponse;
import com.example.diaryproject.dtos.responses.RegisterUserResponse;

import static com.example.diaryproject.utils.AppUtils.*;

public class Mapper {
    private static int count= 1;

    public static User map(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setEmailAddress(registerUserRequest.getEmailAddress());
        user.setPassword(registerUserRequest.getPassword());
        return user;
    }

    public static RegisterUserResponse map(User user) {
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setId((user.getId()));
        registerUserResponse.setMessage( REGISTERED_SUCCESSFULLY);
        return registerUserResponse;
    }
    public static Entry map(CreateEntryRequest createEntryRequest){
        Entry entry = new Entry();
        entry.setBody(createEntryRequest.getBody());
        entry.setTitle(createEntryRequest.getTitle());
        return entry;
    }
    public static Entry map(Entry entry){
        CreateEntryResponse createEntryResponse = new CreateEntryResponse();
        createEntryResponse.setTitle(entry.getTitle());
        createEntryResponse.setMessage(ENTRY_SUCCESSFULLY_CREATED);
        return entry;
    }
    public static Diary map(CreateDiaryRequest createDiaryRequest){
        Diary diary = new Diary();
        createDiaryRequest.setId(count);
        count+=1;
        diary.setUsername(createDiaryRequest.getUsername());
        diary.setId(String.valueOf(createDiaryRequest.getId()));
        diary.setPassword(createDiaryRequest.getPassword());
        diary.setEmailAddress(createDiaryRequest.getEmailAddress());
        return diary;

    }
    public static CreateDiaryResponse mapResponse(Diary diary){
        CreateDiaryResponse createDiaryResponse = new CreateDiaryResponse();
        createDiaryResponse.setId(diary.getId());
        createDiaryResponse.setMessage(DIARY_SUCCESSFULLY_CREATED);
        return createDiaryResponse;
    }
    public static Diary map(DeleteDiaryRequest deleteDiaryRequest){
        Diary diary = new Diary();
        diary.setId(deleteDiaryRequest.getId());
        diary.setUsername(deleteDiaryRequest.getUsername());
        return diary;
    }
    public static CreateDiaryResponse map(Diary diary){
    CreateDiaryResponse createDiaryResponse = new CreateDiaryResponse();
    createDiaryResponse.setMessage(DIARY_SUCCESSFULLY_DELETED);
        return createDiaryResponse;
    }
}
