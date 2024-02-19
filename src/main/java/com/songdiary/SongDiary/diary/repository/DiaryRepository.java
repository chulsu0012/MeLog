package com.songdiary.SongDiary.diary.repository;

import com.songdiary.SongDiary.diary.domain.Diary;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository {
    void save(Diary diary);
    void delete(Long diaryId);
    List<Diary> findAll();
    Optional<Diary> findById(Long diaryId);
    List<Diary> findByDateAndUser(Long userId, LocalDate date);
    List<Diary> findByUser(Long userId);
}
