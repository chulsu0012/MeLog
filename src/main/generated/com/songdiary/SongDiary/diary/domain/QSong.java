package com.songdiary.SongDiary.diary.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSong is a Querydsl query type for Song
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSong extends EntityPathBase<Song> {

    private static final long serialVersionUID = -76163257L;

    public static final QSong song = new QSong("song");

    public final StringPath songArtist = createString("songArtist");

    public final NumberPath<Long> songId = createNumber("songId", Long.class);

    public final NumberPath<Long> songLikes = createNumber("songLikes", Long.class);

    public final StringPath songTitle = createString("songTitle");

    public QSong(String variable) {
        super(Song.class, forVariable(variable));
    }

    public QSong(Path<? extends Song> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSong(PathMetadata metadata) {
        super(Song.class, metadata);
    }

}

