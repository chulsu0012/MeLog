package com.songdiary.SongDiary.diary.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.CreateDiaryRequest;
import com.songdiary.SongDiary.diary.dto.DeleteDiaryRequest;
import com.songdiary.SongDiary.diary.dto.FindDiaryByDateRequest;
import com.songdiary.SongDiary.diary.dto.UpdateDiaryRequest;
import com.songdiary.SongDiary.diary.service.DiaryService;
import com.songdiary.SongDiary.user.dto.UserSessionDTO;
import com.songdiary.SongDiary.user.service.UserService;

@RestController
public class DiaryController {
  
  private final DiaryService diaryService;

  @Autowired
  public DiaryController(DiaryService diaryService, UserService userService) {
    this.diaryService = diaryService;
  }

  @PostMapping("/diary/new")
  public void createDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody CreateDiaryRequest req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    
    Diary diary = new Diary();
    diary.setDiaryWriterId(user.getUserId());
    diary.setDiaryTitle(req.getDiaryTitle());
    diary.setDiaryContents(req.getDiaryContents());

    diaryService.writeDiary(diary);
  }

  @GetMapping("/diary")
  public String getDiaryByDate(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody FindDiaryByDateRequest req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    LocalDate date = (req.getDiaryDate() == null)?LocalDate.now():req.getDiaryDate();
    List<Diary> diaries = diaryService.findDiariesByUserAndDate(user.getUserId(), date);

    return "/diary";
  }
  @GetMapping("/main")
  public String getMainDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, Model model){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    List<Diary> diaries = diaryService.findDiariesByUser(user.getUserId());
    model.addAttribute("diaries", diaries);
      return "/main";
  }

  @DeleteMapping("/diary/{diaryId}/delete")
  public String deleteDiary(@RequestBody DeleteDiaryRequest req){
    diaryService.deleteDiary(req.getDiaryId());
    return "redirect/diary";
  }

  @PutMapping("/diary/{diaryId}/edit")
  public String updateDiary(@RequestBody UpdateDiaryRequest req){  
    diaryService.updateDiary(req);
    return "redirect:/diary";
  }
}
