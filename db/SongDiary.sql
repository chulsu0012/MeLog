drop table if exists SongDiary CASCADE;
create table SongDiary(
    diaryId bigint not null,
    songId bigint not null
);
