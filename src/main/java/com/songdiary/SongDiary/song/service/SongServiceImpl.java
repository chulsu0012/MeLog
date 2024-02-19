package com.songdiary.SongDiary.song.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.repository.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
  @Autowired  
  private final SongRepository songRepository;

    public Long saveSong(Song song){
        songRepository.save(song);
        return song.getSongId();
    }

}
