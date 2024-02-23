package com.songdiary.SongDiary.diary.service;

import java.time.LocalDate;
import java.util.List;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.DiaryRequest;

public interface DiaryService {
    Long writeDiary(Diary diary);
    void deleteDiary(Long diaryId);
    void updateDiary(Long diaryId, DiaryRequest req);
    List<Diary> findAllDiaries();
    Diary findDiaryById(Long diaryId);
    List<Diary> findDiariesByUserAndDate(Long userId, LocalDate diaryDate);
    List<Diary> findDiariesByUser(Long userId);
}