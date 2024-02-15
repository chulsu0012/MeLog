package com.songdiary.SongDiary;

import org.springframework.context.annotation.Configuration;

import com.songdiary.SongDiary.user.repository.UserRepository;

import jakarta.persistence.EntityManager;

@Configuration
public class AppConfig {
  
  private final EntityManager em;
  private final UserRepository userRepository;

  public AppConfig(EntityManager em, UserRepository userRepository) {
    this.em = em;
    this.userRepository = userRepository;
  }
}
