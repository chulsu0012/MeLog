package com.songdiary.SongDiary.diary.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class DiaryRequest {
    private String diaryTitle;
    private String diaryContents;
}
