package com.songdiary.SongDiary;

import com.songdiary.SongDiary.diary.repository.DiaryRepository;
import org.springframework.context.annotation.Configuration;

import com.songdiary.SongDiary.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

@Configuration
public class AppConfig {

    private final EntityManager em;
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    public AppConfig(EntityManager em, UserRepository userRepository, DiaryRepository diaryRepository) {
        this.em = em;
        this.userRepository = userRepository;
        this.diaryRepository = diaryRepository;
    }
}