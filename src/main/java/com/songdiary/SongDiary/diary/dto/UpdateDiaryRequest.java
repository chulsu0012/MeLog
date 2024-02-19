package com.songdiary.SongDiary.diary.dto;

import lombok.Data;

@Data
public class UpdateDiaryRequest {
  Long diaryId;
  String diaryTitle;
  String diaryContents;
}
