package com.songdiary.SongDiary.song.dto;

import lombok.Data;

@Data
public class SongRequest {
    private String songTitle;
    private String songArtist;
    private Long songLikes;
}