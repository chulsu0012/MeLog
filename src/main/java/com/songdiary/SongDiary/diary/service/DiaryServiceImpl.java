package com.songdiary.SongDiary.diary.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.songdiary.SongDiary.diary.dto.DiaryRequest;
import org.springframework.stereotype.Service;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.repository.DiaryRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository diaryRepository;

    @Transactional
    public Long writeDiary(Diary diary){
        diaryRepository.save(diary);
        return diary.getDiaryId();
    }
    @Transactional
    public void deleteDiary(Long diaryId){
        Diary diary = diaryRepository.findByDiaryId(diaryId).get();
        diaryRepository.delete(diary);
    }
    @Transactional
    public void updateDiary(Long diaryId, DiaryRequest req) {
        Optional<Diary> optionalDiary = diaryRepository.findById(diaryId);
        optionalDiary.ifPresent(diary -> {
            diary.setDiaryTitle(req.getDiaryTitle());
            diary.setDiaryContents(req.getDiaryContents());
        });
    }

    public List<Diary> findAllDiaries(){
        return diaryRepository.findAll();
    }

    public Diary findDiaryById(Long diaryId) {
        return diaryRepository.findById(diaryId)
                .orElseThrow(()->new EntityNotFoundException("다이어리가 존재하지 않습니다."));

    }

    public List<Diary> findDiariesByUserAndDate(Long diaryWriterId, LocalDate diaryDate) {
        List<Diary> diaries = diaryRepository.findByDiaryDateAndDiaryWriterId(diaryDate, diaryWriterId);
        if (diaries == null || diaries.isEmpty()) {
            throw new EntityNotFoundException("해당 날짜의 다이어리가 존재하지 않습니다.");
        }
        return diaries;
    }

    public List<Diary> findDiariesByUser(Long diaryWriterId) {
       return diaryRepository.findByDiaryWriterId(diaryWriterId);
    }

}