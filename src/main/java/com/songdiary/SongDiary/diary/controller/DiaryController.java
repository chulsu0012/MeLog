package com.songdiary.SongDiary.diary.controller;

import java.time.LocalDate;
import java.util.List;

import com.songdiary.SongDiary.diary.dto.DiaryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.DiaryRequestDTO;
import com.songdiary.SongDiary.diary.dto.FindDiaryByDateRequest;
import com.songdiary.SongDiary.diary.service.DiaryService;
import com.songdiary.SongDiary.user.dto.UserSessionDTO;

@RestController
@RequiredArgsConstructor
public class DiaryController {
  
  private final DiaryService diaryService;

  @PostMapping("/diary")
  public DiaryResponseDTO createDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody DiaryRequestDTO req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    
    Diary diary = new Diary();
    diary.setDiaryWriterId(user.getUserId());
    diary.setDiaryTitle(req.getTitle());
    diary.setDiaryContents(req.getContents());

    Long diaryId = diaryService.writeDiary(diary);
    return diaryService.findDiaryById(diaryId);
  }

  @GetMapping("/diary")
  public List<DiaryResponseDTO> getDiaryByDate(@SessionAttribute(name="user", required=false) UserSessionDTO user, @RequestBody FindDiaryByDateRequest req){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    LocalDate date = (req.getDate() == null)?LocalDate.now():req.getDate();
    return diaryService.findDiariesByUserAndDate(user.getUserId(), date);
  }
  @GetMapping("/main")
  public List<DiaryResponseDTO> getMainDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }

    return diaryService.findDiariesByUser(user.getUserId());
  }
  @GetMapping("/diary/{diaryId}")
  public DiaryResponseDTO getDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    return diaryService.findDiaryById(diaryId);
  }
  @DeleteMapping("/diary/{diaryId}")
  public List<DiaryResponseDTO> deleteDiary(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId){
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
    diaryService.deleteDiary(diaryId);
    return diaryService.findDiariesByUser(user.getUserId());
  }

  @PutMapping("/diary/{diaryId}")
  public DiaryResponseDTO updateDiary(@PathVariable Long diaryId, @RequestBody DiaryRequestDTO req){
    diaryService.updateDiary(diaryId, req);
    return diaryService.findDiaryById(diaryId);
  }
}
