package com.songdiary.SongDiary.diary.controller;

import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.DiaryRequest;
import com.songdiary.SongDiary.diary.dto.FindDiaryByDateRequest;
import com.songdiary.SongDiary.diary.service.DiaryService;
import com.songdiary.SongDiary.user.dto.UserSessionDTO;
import com.songdiary.SongDiary.user.service.UserService;

@RestController
@RequiredArgsConstructor
public class DiaryController {
  
  private final DiaryService diaryService;

  @PostMapping("/diary/new")
  public Diary createDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody DiaryRequest req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    
    Diary diary = new Diary();
    diary.setDiaryWriterId(user.getUserId());
    diary.setDiaryTitle(req.getDiaryTitle());
    diary.setDiaryContents(req.getDiaryContents());

    Long diaryId = diaryService.writeDiary(diary);
    return diaryService.findDiaryById(diaryId);
  }

  @GetMapping("/diary")
  public List<Diary> getDiaryByDate(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody FindDiaryByDateRequest req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    LocalDate date = (req.getDiaryDate() == null)?LocalDate.now():req.getDiaryDate();
    return diaryService.findDiariesByUserAndDate(user.getUserId(), date);
  }
  @GetMapping("/main")
  public List<Diary> getMainDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    return diaryService.findDiariesByUser(user.getUserId());
  }

  @DeleteMapping("/diary/{diaryId}/delete")
  public List<Diary> deleteDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    diaryService.deleteDiary(diaryId);
    return diaryService.findDiariesByUser(user.getUserId());
  }

  @PutMapping("/diary/{diaryId}/edit")
  public Diary updateDiary(@PathVariable Long diaryId, @RequestBody DiaryRequest req){
    diaryService.updateDiary(diaryId, req);
    return diaryService.findDiaryById(diaryId);
  }
}
