DROP TABLE IF EXISTS book;

CREATE TABLE book (
    id serial,
    author varchar(100),
    isbn varchar(100),
    title varchar(100),
    price float
);

