package com.songdiary.SongDiary.diary.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SongForm {
    private Long songId;
    private String songTitle;
    private String songArtist;
    private Long songLikes;
}
