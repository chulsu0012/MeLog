package com.songdiary.SongDiary.diary.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.songdiary.SongDiary.song.domain.Song;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="Diary")
public class Diary {
  @Column(name="DIARYID")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long diaryId;

  @Column(name="DIARYWRITERID")
  private Long diaryWriterId;

  @Column(name="DIARYTITLE")
  private String diaryTitle;

  @Column(name="DIARYDATE")
  @CreationTimestamp
  private LocalDate diaryDate;

  @Column(name="DIARYCONTENTS")
  private String diaryContents;

  @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Song> diarySongs = new ArrayList<>();

  //==연관관계 메서드==//
  public void addDiarySong(Song song){
    diarySongs.add(song);
    song.setDiary(this);
  }

}