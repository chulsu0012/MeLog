package com.songdiary.SongDiary.diary.service;

import com.songdiary.SongDiary.diary.domain.Diary;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryService {
    Long writeDiary(Diary diary);
    void deleteDiary(Long diaryId);
    void updateDiary(Long diaryId, String diaryTitle, String diaryContents, LocalDate diaryDate);
    List<Diary> findDiaries();
    Diary findDiaryById(Long diaryId);
    List<Diary> findDiariesByUserAndDate(Long userId, LocalDate diaryDate);
    List<Diary> findDiariesByUser(Long userId);
}
