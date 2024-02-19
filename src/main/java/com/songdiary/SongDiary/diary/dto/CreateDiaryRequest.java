package com.songdiary.SongDiary.diary.dto;

import lombok.Data;

@Data
public class CreateDiaryRequest {
    private String diaryTitle;
    private String diaryContents;
}
