drop table if exists Diary CASCADE;
create table Diary(
    diaryId bigint not null auto_increment,
    diaryWriterId bigint not null,
    diaryTitle varchar not null,
    diaryDate date not null,
    diaryContents text not null,
    primary key(diaryId)
);
