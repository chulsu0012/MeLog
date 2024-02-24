package com.songdiary.SongDiary.diary.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;

@Data
public class FindDiaryByDateRequest {
  LocalDate date;
}
