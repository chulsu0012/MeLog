package com.songdiary.SongDiary.song.service;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.dto.DiaryRequest;
import com.songdiary.SongDiary.diary.repository.DiaryRepository;
import com.songdiary.SongDiary.song.dto.SongRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.repository.SongRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

  private final SongRepository songRepository;
  private final DiaryRepository diaryRepository;
  @Transactional
  public void createSongs(Long diaryId, List<SongRequest> reqs){
      Diary diary = diaryRepository.findByDiaryId(diaryId)
              .orElseThrow(()->new EntityNotFoundException("Diary not found with id "+diaryId));
      for(SongRequest req : reqs){
          Song song = new Song();
          song.setSongTitle(req.getSongTitle());
          song.setSongArtist(req.getSongArtist());
          song.setSongLikes(req.getSongLikes());

          songRepository.save(song);
          diary.addDiarySong(song);
      }
      diaryRepository.save(diary);
  }
  @Transactional
  public void deleteSongs(Long diaryId) {
      Diary diary = diaryRepository.findByDiaryId(diaryId)
                .orElseThrow(()->new EntityNotFoundException("Diary not found with id "+diaryId));
      List<Song> songToDelete = new ArrayList<>(diary.getDiarySongs());
      for(Song song : songToDelete){
          songRepository.delete(song);
      }

      diary.getDiarySongs().clear();
      diaryRepository.save(diary);
  }
  public List<Song> findSongsByDiaryId(Long diaryId) {
      return songRepository.findByDiaryDiaryId(diaryId);
  }

}
