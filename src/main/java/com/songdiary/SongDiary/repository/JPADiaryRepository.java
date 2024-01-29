package com.songdiary.SongDiary.repository;

import com.songdiary.SongDiary.domain.Diary;

import jakarta.persistence.EntityManager;

public class JPADiaryRepository implements DiaryRepository {
 
  public final EntityManager em;

  public JPADiaryRepository(EntityManager em) {
    this.em = em;
  }

  @Override
  public Diary save(Diary diary) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }
  
}
