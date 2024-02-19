package com.songdiary.SongDiary.diary.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FindDiaryByDateRequest {
  LocalDate diaryDate;
}
