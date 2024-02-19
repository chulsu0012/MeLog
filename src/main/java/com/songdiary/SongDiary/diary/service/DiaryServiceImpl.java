package com.songdiary.SongDiary.diary.service;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.repository.DiaryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Override
    @Transactional
    public Long writeDiary(Diary diary){
        diaryRepository.save(diary);
        return diary.getDiaryId();
    }

    @Override
    @Transactional
    public void deleteDiary(Long diaryId){
        diaryRepository.delete(diaryId);
    }

    @Override
    public void updateDiary(Long diaryId, String diaryTitle, String diaryContents, LocalDate diaryDate) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        optionalDiary.ifPresent(diary -> {
            diary.setDiaryTitle(diaryTitle);
            diary.setDiaryContents(diaryContents);
            diary.setDiaryDate(diaryDate);
        });
    }

    @Override
    public List<Diary> findDiaries(){
        return diaryRepository.findAll();
    }

    @Override
    public Diary findDiaryById(Long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(()->new EntityNotFoundException("Diary not found with id: " + diaryId));
    }

    public List<Diary> findDiariesByUserAndDate(Long userId, LocalDate diaryDate) {
        List<Diary> diaries = diaryRepository.findByDateAndUser(userId, diaryDate);
        if (diaries == null || diaries.isEmpty()) {
            throw new EntityNotFoundException("No diaries found for the given date");
        }
        return diaries;
    }

    @Override
    public List<Diary> findDiariesByUser(Long userId) {
       return diaryRepository.findByUser(userId);
    }

}
