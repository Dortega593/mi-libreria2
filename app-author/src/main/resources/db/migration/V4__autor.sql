INSERT INTO
    author(first_name, last_name)
VALUES
    ('author1', 'author1'),
    ('author2', 'author2'),
    ('author3', 'author3'),
    ('author4', 'author4');

UPDATE book SET author_id = 1 WHERE id = 1;
UPDATE book SET author_id = 2 WHERE id = 2;
UPDATE book SET author_id = 3 WHERE id = 3;
UPDATE book SET author_id = 4 WHERE id = 4;