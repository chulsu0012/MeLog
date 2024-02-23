package com.songdiary.SongDiary.emotion.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.songdiary.SongDiary.emotion.dto.EmotionDTO;
import com.songdiary.SongDiary.emotion.service.EmotionService;
import com.songdiary.SongDiary.user.dto.UserSessionDTO;


@RestController
@RequestMapping("/emotion")
public class EmotionController {

  private final EmotionService emotionService;

  @Autowired
  public EmotionController(EmotionService emotionService) {
    this.emotionService = emotionService;
  }

  @PostMapping("{diaryId}")
  public ResponseEntity<?> createEmotion(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId, @RequestBody EmotionDTO req) {
    if (user == null || user.getUserId() == null) {
      return new ResponseEntity<>("로그인 후 이용해주세요.", HttpStatus.UNAUTHORIZED);
    }

    try {
      emotionService.createEmotion(diaryId, req);
      return new ResponseEntity<>("다이어리 감정 분석이 완료되었습니다.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("{diaryId}")
  public ResponseEntity<?> findEmotion(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId) {
    if (user == null || user.getUserId() == null) {
      return new ResponseEntity<>("로그인 후 이용해주세요.", HttpStatus.UNAUTHORIZED);
    }

    try {
      Optional<EmotionDTO> emotion = emotionService.findEmotionByDiaryId(diaryId);
      return ResponseEntity.ok(emotion.get());
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("{diaryId}")
  public ResponseEntity<?> deleteEmotion(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId) {
    if (user == null || user.getUserId() == null) {
      return new ResponseEntity<>("로그인 후 이용해주세요.", HttpStatus.UNAUTHORIZED);
    }
    try {
      emotionService.deleteEmotion(diaryId);
      return new ResponseEntity<>("다이어리 감정이 성공적으로 제거되었습니다.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  

  
}
