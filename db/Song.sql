drop table if exists Song CASCADE;
create table Song(
    songId bigint not null auto_increment,
    songTitle varchar not null,
    sontArtist varchar not null,
    songLikes bigint not null,
    primary key(songId)
);
