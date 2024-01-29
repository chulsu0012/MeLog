package com.songdiary.SongDiary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.songdiary.SongDiary.repository.DiaryRepository;
import com.songdiary.SongDiary.repository.JPADiaryRepository;

import jakarta.persistence.EntityManager;

@Configuration
public class AppConfig {
  
  private final EntityManager em;

  public AppConfig(EntityManager em) {
    this.em = em;
  }

  //Diary
  @Bean
  public DiaryRepository diaryRepository() {
    return new JPADiaryRepository(em);
  }
}
