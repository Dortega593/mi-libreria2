package client.srv;

import client.dto.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();
    Author findById(String id);
    void save(Author singer);
    void delete(Integer id);
    void update(Integer id, Author singer);

}
