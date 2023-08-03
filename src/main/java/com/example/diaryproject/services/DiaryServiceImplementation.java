package com.example.diaryproject.services;

import com.example.diaryproject.Data.models.Diary;
import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.repository.DiaryRepository;
import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.CreateEntryResponse;
import com.example.diaryproject.dtos.responses.LoginDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.exceptions.DiaryUsernameAlreadyExistExceptions;
import com.example.diaryproject.exceptions.WrongPasswordException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import static com.example.diaryproject.utils.Mapper.map;
import static com.example.diaryproject.utils.Mapper.mapResponse;


@Service
@AllArgsConstructor
public class DiaryServiceImplementation implements DiaryService {

    private DiaryRepository diaryRepository;
    private EntryService entryService;

    @Override
    public CreateDiaryResponse createDiary(CreateDiaryRequest createDiaryRequest) {
        validateDuplicateUsername(createDiaryRequest);
        Diary diary = map(createDiaryRequest);
        diaryRepository.save(diary);
        return mapResponse(diary);
    }

    @Override
    public LoginDiaryResponse loginDiary(LoginDiaryRequest loginDiaryRequest) throws WrongPasswordException, DiaryDoesNotExistException {
        Diary foundDiary = diaryRepository.findByUsername(loginDiaryRequest.getUsername());
        if (foundDiary == null)
            throw new DiaryDoesNotExistException("Username Does Not Exist, Kindly Enter a Valid Username ->");
        boolean isLoggedIn;
        if (!Objects.equals(foundDiary.getPassword(), loginDiaryRequest.getPassword())) {
            throw new WrongPasswordException("Wrong password, please Enter correct password to Login to Diary");
        } else if (foundDiary.getPassword().equals(loginDiaryRequest.getPassword()))
            isLoggedIn = true;
        LoginDiaryResponse loginDiaryResponse = new LoginDiaryResponse();
        loginDiaryResponse.setId(Integer.parseInt(foundDiary.getId()));
        LocalDateTime loginTime = LocalDateTime.now();
        String loginMessage = getLoginMessage(loginTime);
        loginDiaryResponse.setMessage(loginMessage);
        return loginDiaryResponse;
    }

    private static String getLoginMessage(LocalDateTime loginTime) {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = loginTime.format(myDateFormat);
        return "Login just occurred on your SultyBank account at " + formattedDateTime + "\n" +
                "Please if you did not initiate this action, contact our customer support on support@sultybank.com or 08138956052 as your account might have been compromised. \n" +
                "Kind Regards, \n" +
                "SultyBank Team \n";
    }

    @Override
    public List<Diary> findAllDairy() {
        return diaryRepository.findAll();
    }

    @Override
    public long count() {
        return diaryRepository.count();
    }
    public CreateEntryResponse createEntry(CreateEntryRequest createEntryRequest){
        for (Diary diary: findAllDairy()) {
            diary.getEntries().add(entryService.createEntry(createEntryRequest));
            new Entry();
            return null;
        }

        return null;
    }

    @Override
    public void deleteAll() {
        diaryRepository.deleteAll();
    }

    private void validateDuplicateUsername(CreateDiaryRequest createDiaryRequest) {
        boolean usernameExist = confirmUsername(createDiaryRequest);
        if (usernameExist) throw new DiaryUsernameAlreadyExistExceptions("username already Exist, kindly enter a valid username :");

    }

    private boolean confirmUsername(CreateDiaryRequest createDiaryRequest) {
        Diary diary = diaryRepository.findByUsername(createDiaryRequest.getUsername());
        return diary != null;
    }

}
