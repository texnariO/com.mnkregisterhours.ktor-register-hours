create table if not exists "user"(
    id serial primary key unique not null,
    username varchar(150) not null,
    pin varchar(4) not null unique
)