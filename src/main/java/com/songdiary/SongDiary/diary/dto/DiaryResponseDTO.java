package com.songdiary.SongDiary.diary.dto;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.emotion.domain.Emotion;
import com.songdiary.SongDiary.emotion.dto.EmotionDTO;
import com.songdiary.SongDiary.song.domain.Song;
import com.songdiary.SongDiary.song.dto.SongDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@Builder
public class DiaryResponseDTO {
    private Long id;
    private Long writerId;
    private String title;
    private LocalDate date;
    private String contents;
    private List<SongDTO> songs;
    private EmotionDTO emotion;

    public static DiaryResponseDTO from(Diary entity){
        List<SongDTO> songDTOs = new ArrayList<>();
        for (Song song : entity.getDiarySongs()) {
            SongDTO songDTO = new SongDTO();
            songDTO.setTitle(song.getSongTitle());
            songDTO.setArtist(song.getSongArtist());
            songDTO.setLikes(song.getSongLikes());

            songDTOs.add(songDTO);
        }

        EmotionDTO emotionDTO = new EmotionDTO();
        if(entity.getDiaryEmotion()!=null){
            emotionDTO.setHappiness(entity.getDiaryEmotion().getHappiness());
            emotionDTO.setSadness(entity.getDiaryEmotion().getSadness());
            emotionDTO.setNeutral(entity.getDiaryEmotion().getNeutral());
            emotionDTO.setFear(entity.getDiaryEmotion().getFear());
            emotionDTO.setSurprise(entity.getDiaryEmotion().getSurprise());
            emotionDTO.setAnger(entity.getDiaryEmotion().getAnger());
        }

        return DiaryResponseDTO.builder()
                .id(entity.getDiaryId())
                .writerId(entity.getDiaryWriterId())
                .title(entity.getDiaryTitle())
                .date(entity.getDiaryDate())
                .contents(entity.getDiaryContents())
                .songs(songDTOs)
                .emotion(emotionDTO)
                .build();
    }
}
