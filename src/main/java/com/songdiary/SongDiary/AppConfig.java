package com.songdiary.SongDiary;

import org.springframework.context.annotation.Configuration;

import com.songdiary.SongDiary.diary.repository.DiaryRepository;
import com.songdiary.SongDiary.song.repository.SongRepository;
import com.songdiary.SongDiary.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

@Configuration
public class AppConfig {
  
  private final EntityManager em;
  private final UserRepository userRepository;
  private final DiaryRepository diaryRepository;
  private final SongRepository songRepository;

  public AppConfig(EntityManager em, UserRepository userRepository, DiaryRepository diaryRepository, SongRepository songRepository) {
    this.em = em;
    this.userRepository = userRepository;
    this.diaryRepository = diaryRepository;
    this.songRepository = songRepository;

  }
}
