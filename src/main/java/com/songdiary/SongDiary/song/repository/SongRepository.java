package com.songdiary.SongDiary.song.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.songdiary.SongDiary.song.domain.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
  Optional<Song> findById(Long songId);
}
