package com.songdiary.SongDiary.diary.repository;

import com.songdiary.SongDiary.diary.domain.Song;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SongRepositoryImpl implements SongRepository {
    private final EntityManager em;

    @Override
    public void save(Song song) {
        em.persist(song);
    }

    @Override
    public Optional<Song> findById(Long songId) {
        return Optional.ofNullable(em.find(Song.class, songId));
    }
}
