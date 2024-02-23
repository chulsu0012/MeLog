drop table if exists AppSong CASCADE;
create table AppSong(
    songId bigint not null auto_increment,
    songTitle varchar not null,
    songArtist varchar not null,
    songLikes bigint not null,
    primary key(songId)
);
