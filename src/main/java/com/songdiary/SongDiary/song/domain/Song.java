package com.songdiary.SongDiary.song.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Song")
public class Song {

  @Column(name="SONGID")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long songId;

  @Column(name="SONGTITLE")
  private String songTitle;

  @Column(name="SONGARTIST")
  private String songArtist;

  @Column(name="SONGLIKES")
  private Long songLikes;

}