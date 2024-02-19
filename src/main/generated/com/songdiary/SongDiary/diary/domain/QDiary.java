package com.songdiary.SongDiary.diary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiary is a Querydsl query type for Diary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDiary extends EntityPathBase<Diary> {

    private static final long serialVersionUID = 1919862737L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiary diary = new QDiary("diary");

    public final StringPath diaryContents = createString("diaryContents");

    public final DatePath<java.time.LocalDate> diaryDate = createDate("diaryDate", java.time.LocalDate.class);

    public final NumberPath<Long> diaryId = createNumber("diaryId", Long.class);

    public final ListPath<Song, QSong> diarySongs = this.<Song, QSong>createList("diarySongs", Song.class, QSong.class, PathInits.DIRECT2);

    public final StringPath diaryTitle = createString("diaryTitle");

    public final com.songdiary.SongDiary.user.domain.QUser diaryWriter;

    public QDiary(String variable) {
        this(Diary.class, forVariable(variable), INITS);
    }

    public QDiary(Path<? extends Diary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiary(PathMetadata metadata, PathInits inits) {
        this(Diary.class, metadata, inits);
    }

    public QDiary(Class<? extends Diary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diaryWriter = inits.isInitialized("diaryWriter") ? new com.songdiary.SongDiary.user.domain.QUser(forProperty("diaryWriter")) : null;
    }

}

