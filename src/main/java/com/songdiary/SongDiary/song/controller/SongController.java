package com.songdiary.SongDiary.song.controller;

import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.dto.SongRequest;
import com.songdiary.SongDiary.song.service.SongService;
import com.songdiary.SongDiary.user.dto.UserSessionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    @PostMapping("/{diaryId}")
    public List<Song> createSong(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId, @RequestBody List<SongRequest> reqs){
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
        }
        songService.createSongs(diaryId, reqs);
        return songService.findSongsByDiaryId(diaryId);
    }
    @PutMapping("/{diaryId}")
    public List<Song> updateSong(@SessionAttribute(name="user", required=false) UserSessionDTO user, @PathVariable Long diaryId, @RequestBody List<SongRequest> reqs) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
        }
        songService.deleteSongs(diaryId);
        songService.createSongs(diaryId, reqs);
        return songService.findSongsByDiaryId(diaryId);
    }
}
