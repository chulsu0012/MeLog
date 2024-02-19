drop table if exists "user" CASCADE;
create table "user"(
    userId bigint not null auto_increment,
    userProfileId varchar not null,
    userPassword varchar not null,
    userName varchar not null,
    primary key(userId)
);
