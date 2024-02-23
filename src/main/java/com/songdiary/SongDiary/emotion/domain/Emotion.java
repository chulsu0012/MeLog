package com.songdiary.SongDiary.emotion.domain;

import com.songdiary.SongDiary.diary.domain.Diary;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="AppEmotion")
public class Emotion {

  @Column(name="EMOTIONID")
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long emotionId;

  @Column(name="HAPPINESS")
  private Long happiness;

  @Column(name="NEUTRAL")
  private Long neutral;

  @Column(name="SADNESS")
  private Long sadness;

  @Column(name="ANGER")
  private Long anger;

  @Column(name="SURPRISE")
  private Long surprise;

  @Column(name="FEAR")
  private Long fear;

  @OneToOne
  @JoinColumn(name="diaryId")
  private Diary diary;

}