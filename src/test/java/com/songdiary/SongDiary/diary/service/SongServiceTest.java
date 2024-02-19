package com.songdiary.SongDiary.diary.service;

import com.songdiary.SongDiary.diary.domain.Song;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SongServiceTest {
    @Autowired
    SongService songService;
    @Autowired
    EntityManager em;

    @Test
    public void 노래등록() throws Exception{
        //given
        Song song = createSong();

        //when
        Long songId = songService.saveSong(song);

        //then
        Assertions.assertThat(songId).isEqualTo(song.getSongId());
    }

    private Song createSong() {
        Song song = new Song();
        song.setSongArtist("artist");
        song.setSongTitle("title");
        song.setSongLikes(0L);
        return song;
    }
}