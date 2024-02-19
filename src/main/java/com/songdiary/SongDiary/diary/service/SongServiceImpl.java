package com.songdiary.SongDiary.diary.service;

import com.songdiary.SongDiary.diary.domain.Song;
import com.songdiary.SongDiary.diary.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    @Transactional
    public Long saveSong(Song song){
        songRepository.save(song);
        return song.getSongId();
    }

    @Override
    public Song findSongById(Long songId){
        return songRepository.findById(songId)
                .orElseThrow(()->new EntityNotFoundException("Diary not found with id: " + songId));
    }

}
