create table users
(
    id       int(11) not null auto_increment,
    name     varchar(128) not null,
    surname  varchar(128) not null,
    age      int(4) not null,
    email    varchar(128) not null unique,
    password varchar(128) not null,

    primary key (id)
);


create table books
(
    id       int(11) not null auto_increment,
    name     varchar(128) not null,
    created  timestamp not null default CURRENT_TIMESTAMP,
    author_id      int(11) not null ,
    FOREIGN KEY (author_id) REFERENCES users(id),
    primary key (id)
);

alter table users_books_app.books
    add page int(11) not null;