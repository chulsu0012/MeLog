package com.songdiary.SongDiary.diary.controller;

import com.songdiary.SongDiary.diary.domain.Song;
import com.songdiary.SongDiary.diary.service.SongServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SongController {
    private final SongServiceImpl songService;

    @GetMapping("/song/new")
    public String createForm(Model model){
        model.addAttribute("form", new SongForm());
        return "song/createSongForm";
    }
    @PostMapping("/song/new")
    public String createSong(@Valid SongForm songForm){
        Song song = new Song();
        song.setSongId(songForm.getSongId());
        song.setSongTitle(songForm.getSongTitle());
        song.setSongArtist(songForm.getSongArtist());
        song.setSongLikes(songForm.getSongLikes());

        songService.saveSong(song);
        return "redirect:/";
    }
}
