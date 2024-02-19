package com.songdiary.SongDiary.diary.controller;

import com.songdiary.SongDiary.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class DiaryForm {
    private Long diaryId;
    private String diaryTitle;
    private LocalDate diaryDate;
    private User diaryWriter;
    private String diaryContents;

}
