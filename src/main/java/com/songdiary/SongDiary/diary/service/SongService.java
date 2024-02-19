package com.songdiary.SongDiary.diary.service;

import com.songdiary.SongDiary.diary.domain.Song;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SongService {
    Long saveSong(Song song);
    Song findSongById(Long songId);
}
