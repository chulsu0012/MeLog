package com.songdiary.SongDiary.diary.repository;

import com.songdiary.SongDiary.diary.domain.Song;

import java.util.Optional;

public interface SongRepository {
    void save(Song song);
    Optional<Song> findById(Long songId);
}
