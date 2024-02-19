package com.songdiary.SongDiary.song.dto;

import lombok.Data;

@Data
public class SongDTO {
    private Long songId;
    private String songTitle;
    private String songArtist;
    private Long songLikes;
}