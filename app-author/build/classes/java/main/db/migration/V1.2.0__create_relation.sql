CREATE TABLE author (
    id serial primary key not null,
    first_name varchar(100),
    last_name varchar(100)
);

ALTER TABLE book
DROP COLUMN author,
    ADD COLUMN author_id integer,
    ADD CONSTRAINT fk_author_book
    FOREIGN KEY (author_id)
    REFERENCES author(id);