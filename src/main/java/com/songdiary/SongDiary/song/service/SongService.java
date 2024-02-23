package com.songdiary.SongDiary.song.service;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.dto.SongRequest;

import java.util.List;

public interface SongService {

  void createSongs(Long diaryId, List<SongRequest> reqs);
  void deleteSongs(Long diaryId);
  List<Song> findSongsByDiaryId(Long diaryId);
}
