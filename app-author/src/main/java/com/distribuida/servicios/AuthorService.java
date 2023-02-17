package com.distribuida.servicios;

import com.distribuida.db.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Long id);

    void save(Author author);

    void update(long id, Author author) throws Exception;

    void delete(Long id);

}
