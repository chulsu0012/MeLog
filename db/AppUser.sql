drop table if exists AppUser CASCADE;
create table AppUser(
    userId bigint not null auto_increment,
    userProfileId varchar not null,
    userPassword varchar not null,
    userName varchar not null,
    primary key(userId)
);
