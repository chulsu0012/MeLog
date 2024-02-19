package com.songdiary.SongDiary.diary.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.songdiary.SongDiary.diary.domain.Diary;
import com.songdiary.SongDiary.diary.domain.QDiary;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DiaryRepositoryImpl implements DiaryRepository{
    public final EntityManager em;
    public final JPAQueryFactory query;

    @Override
    public void save(Diary diary) {
        if(diary.getDiaryId()==null){ //new
            em.persist(diary);
        }else{//edit
            em.merge(diary);
        }
    }

    @Override
    public void delete(Long diaryId) {
        Diary diary = em.find(Diary.class, diaryId );
        em.remove(diary);
    }

    @Override
    public List<Diary> findAll() {
        QDiary diary = QDiary.diary;
        return query
                .selectFrom(diary)
                .fetch();
    }

    @Override
    public Optional<Diary> findById(Long diaryId) {
        return Optional.ofNullable(em.find(Diary.class, diaryId));
    }

    @Override
    public List<Diary> findByDateAndUser(Long userId, LocalDate date) {
        QDiary diary = QDiary.diary;

        return query
                .selectFrom(diary)
                .where(diary.diaryWriter.userId.eq(userId)
                        .and(diary.diaryDate.eq(date)))
                .fetch();
    }

    @Override
    public List<Diary> findByUser(Long userId) {
        QDiary diary = QDiary.diary;

        return query
                .selectFrom(diary)
                .where(diary.diaryWriter.userId.eq(userId))
                .fetch();
    }


}
