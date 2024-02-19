package com.songdiary.SongDiary.diary.domain;

import com.songdiary.SongDiary.diary.exception.NotEnoughLikeException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Song")
@Getter @Setter
public class Song {

    @Id @GeneratedValue
    @Column(name="SONGID")
    private Long songId;

    @Column(name="SONGTITLE")
    private String songTitle;

    @Column(name="SONGARTIST")
    private String songArtist;

    @Column(name="SONGLIKES")
    private Long songLikes;

    //==생성 메서드==//
//    public static Song createSong(String songTitle, String songArtist, Long songLikes){
//        Song song = new Song();
//        song.setSongTitle(songTitle);
//        song.setSongArtist(songArtist);
//        song.setSongLikes(songLikes);
//
//        return song;
//    }
    //==비지니스 로직==//
    public void addLike(int like){
        this.songLikes +=like;
    }
    public void removeLike(int like){
        long restLikes = this.songLikes-like;
        if(restLikes<0){
            throw new NotEnoughLikeException("like is negative");
        }
        this.songLikes=restLikes;
    }
}
