package com.songdiary.SongDiary.repository;

import com.songdiary.SongDiary.domain.Diary;

public interface DiaryRepository {
  Diary save(Diary diary);
}
