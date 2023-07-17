package com.example.diaryproject.services;
import com.example.diaryproject.Data.models.Diary;
import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.repository.DiaryRepository;
import com.example.diaryproject.Data.repository.DiaryRepositoryImplementation;
import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.LoginDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.exceptions.UsernameAlreadyExistExceptions;
import com.example.diaryproject.exceptions.WrongPasswordException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.diaryproject.utils.Mapper.map;
import static com.example.diaryproject.utils.Mapper.mapResponse;

public class DiaryServiceImplementation implements DiaryService {
    DiaryRepository diaryRepository = new DiaryRepositoryImplementation();
    List<Entry> entries = new ArrayList<>();
    private boolean isLoggedIn;

    @Override
    public CreateDiaryResponse createDiary(CreateDiaryRequest createDiaryRequest) {
        validateDuplicateUsername(createDiaryRequest);
        Diary diary = map(createDiaryRequest);
        diaryRepository.save(diary);
        return mapResponse(diary);
    }

    @Override
    public LoginDiaryResponse loginDiary(LoginDiaryRequest loginDiaryRequest) throws WrongPasswordException, DiaryDoesNotExistException {
        Diary foundDiary = diaryRepository.findBy(loginDiaryRequest.getUsername());
        if (foundDiary == null)
            throw new DiaryDoesNotExistException("Username Does Not Exist, Kindly Enter a Valid Username ->");
        if (!Objects.equals(foundDiary.getPassword(), loginDiaryRequest.getPassword())) {
            throw new WrongPasswordException("Wrong password, please Enter correct password to Login to Diary");
        } else if (foundDiary.getPassword().equals(loginDiaryRequest.getPassword()))
            isLoggedIn = true;
        LoginDiaryResponse loginDiaryResponse = new LoginDiaryResponse();
        loginDiaryResponse.setId(foundDiary.getId());
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        loginDiaryResponse.setMessage("Login successfully at" + myFormat.format(foundDiary.getDateTime()));
        return loginDiaryResponse;
    }

    @Override
    public List<Diary> findAllDairy() {
        return diaryRepository.findAll();
    }

    @Override
    public long count() {
        return diaryRepository.count();
    }
    public void createEntry(CreateEntryRequest createEntryRequest){
        EntryService entryService = new EntryServiceImplementation();
        for (Diary diary: findAllDairy()) {
            diary.getEntries().add(entryService.createEntry(createEntryRequest));
            new Entry();
            return;
        }

    }

    private void validateDuplicateUsername(CreateDiaryRequest createDiaryRequest) {
        boolean usernameExist = confirmUsername(createDiaryRequest);
        if (usernameExist) throw new UsernameAlreadyExistExceptions("username already Exist, kindly enter a valid username :");

    }

    private boolean confirmUsername(CreateDiaryRequest createDiaryRequest) {
        Diary diary = diaryRepository.findBy(createDiaryRequest.getUsername());
        return diary != null;
    }

}
