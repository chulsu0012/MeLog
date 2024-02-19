package com.songdiary.SongDiary.diary.domain;

import com.songdiary.SongDiary.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Diary")
@Getter @Setter
public class Diary {
    @Id @GeneratedValue
    private Long diaryId;
    private String diaryTitle;
    private LocalDate diaryDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="userId")
    private User diaryWriter;
    private String diaryContents;

    @OneToMany
    @JoinTable(name="RecommendedSong",joinColumns =@JoinColumn(name="diaryId"), inverseJoinColumns = @JoinColumn(name="songId"))
    private List<Song> diarySongs = new ArrayList<>();


}
