package com.songdiary.SongDiary.diary.domain;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

}