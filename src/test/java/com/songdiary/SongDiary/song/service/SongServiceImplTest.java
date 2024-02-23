package com.songdiary.SongDiary.song.service;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.DiaryRequest;
import com.songdiary.SongDiary.diary.service.DiaryService;
import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.dto.SongRequest;
import com.songdiary.SongDiary.user.domain.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class SongServiceImplTest {
    @Autowired
    DiaryService diaryService;
    @Autowired SongService songService;
    @Autowired
    EntityManager em;
    @Test
    @Rollback(false)
    public void 노래등록() throws Exception{
        //given
        User user = createUser();
        Diary diary = createDiary(user);
        List<SongRequest> reqs = createSongs();

        //when
        Long diaryId = diaryService.writeDiary(diary);
        songService.createSongs(diaryId, reqs);
        Diary getDiary = diaryService.findDiaryById(diaryId);
        List<Song> getSongs = songService.findSongsByDiaryId(diaryId);

        //then
        assertThat(getSongs).hasSize(3);
        assertThat(getSongs.get(0).getSongArtist()).isEqualTo("artist1");
        assertThat(getSongs.get(1).getSongArtist()).isEqualTo("artist2");
        assertThat(getSongs.get(2).getSongArtist()).isEqualTo("artist3");
    }

    @Test
    @Rollback(false)
    public void 노래삭제() throws Exception{
        //given
        User user = createUser();
        Diary diary = createDiary(user);
        List<SongRequest> reqs = createSongs();

        //when
        Long diaryId = diaryService.writeDiary(diary);
        songService.createSongs(diaryId, reqs);
        songService.deleteSongs(diaryId);
        Diary getDiary = diaryService.findDiaryById(diaryId);
        List<Song> getSongs = songService.findSongsByDiaryId(diaryId);

        //then
        assertThat(getSongs).hasSize(0);

    }

    private List<SongRequest> createSongs() {
        List<SongRequest> reqs = new ArrayList<>();
        SongRequest req = new SongRequest();
        req.setSongTitle("title1");
        req.setSongArtist("artist1");
        req.setSongLikes(0L);

        SongRequest req2 = new SongRequest();
        req2.setSongTitle("title2");
        req2.setSongArtist("artist2");
        req2.setSongLikes(100L);

        SongRequest req3 = new SongRequest();
        req3.setSongTitle("title3");
        req3.setSongArtist("artist3");
        req3.setSongLikes(5L);

        reqs.add(req);
        reqs.add(req2);
        reqs.add(req3);

        return reqs;
    }

    private User createUser() {
        User user = new User();
        user.setUserPassword("password");
        user.setUserName("gimijin");
        user.setUserProfileId("inging007");
        em.persist(user);
        return user;
    }
    private Diary createDiary(User user) {
        Diary diary = new Diary();
        diary.setDiaryTitle("test");
        diary.setDiaryContents("hello world");
        diary.setDiaryWriterId(user.getUserId());
        return diary;
    }
}