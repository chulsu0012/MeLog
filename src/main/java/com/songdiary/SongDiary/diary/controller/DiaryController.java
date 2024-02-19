package com.songdiary.SongDiary.diary.controller;

import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.service.DiaryServiceImpl;
import com.songdiary.SongDiary.user.domain.User;
import com.songdiary.SongDiary.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryServiceImpl diaryService;
    private final UserService userService;

    @GetMapping("/diary/new")
    public String createDiaryForm(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("diaryForm", new DiaryForm());
        return "diary/diaryForm";
    }

    @PostMapping("/diary/new")
    public String createDiary(@Valid DiaryForm diaryForm){
        Diary diary = new Diary();
        diary.setDiaryWriter(diaryForm.getDiaryWriter());
        diary.setDiaryContents(diaryForm.getDiaryContents());
        diary.setDiaryTitle(diaryForm.getDiaryTitle());
        diary.setDiaryId(diaryForm.getDiaryId());
        diaryService.writeDiary(diary);
        return "redirect:/diary";
    }

    @GetMapping("/diary")
    public String getDiaryByDate(@RequestParam(value = "diaryDate", required = false) LocalDate diaryDate, @RequestParam("userId") Long userId, Model model){
        LocalDate date = (diaryDate==null)?LocalDate.now():diaryDate;
        List<Diary> diaries = diaryService.findDiariesByUserAndDate(userId, diaryDate);
        model.addAttribute("diaries", diaries);
        return "/diary";
    }
    @GetMapping("/main")
    public String getMainDiary(@RequestParam("userId") Long userId, Model model){
        List<Diary> diaries = diaryService.findDiariesByUser(userId);
        model.addAttribute("diaries", diaries);
        return "/main";
    }

    @DeleteMapping("/diary/{diaryId}/delete")
    public String deleteDiary(@PathVariable("diaryId") Long diaryId){
        diaryService.deleteDiary(diaryId);
        return "redirect/diary";
    }

    @GetMapping("/diary/{diaryId}/edit")
    public String updateDiaryForm(@PathVariable("diaryId") Long diaryId, Model model){
        Diary diary = diaryService.findDiaryById(diaryId);
        DiaryForm diaryForm = new DiaryForm();
        diaryForm.setDiaryContents(diary.getDiaryContents());
        diaryForm.setDiaryWriter(diary.getDiaryWriter());
        diaryForm.setDiaryTitle(diary.getDiaryTitle());
        diaryForm.setDiaryId(diary.getDiaryId());

        model.addAttribute("diaryForm", diaryForm);
        return "diary/updateDiaryForm";
    }
    @PutMapping("/diary/{diaryId}/edit")
    public String updateDiary(@PathVariable("diaryId") Long diaryId, @ModelAttribute("diaryForm") DiaryForm diaryForm){
        diaryService.updateDiary(diaryId, diaryForm.getDiaryTitle(),diaryForm.getDiaryContents(), diaryForm.getDiaryDate());
        return "redirect:/diary";
    }
}
