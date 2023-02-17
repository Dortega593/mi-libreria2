package client.srv;

import client.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Service
public class BookServiceImpl implements BookService {

    private static final String URL = "http://localhost:7070/books";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Book> getAll() {
        var response = restTemplate.exchange(
                URL,
                GET,
                null,
                new ParameterizedTypeReference<List<Book>>() {
                }
        );
        return response.getBody();
    }

    @Override
    public Book findById(String id) {
        ResponseEntity<Book> res = restTemplate.getForEntity(URL + "/" + id, Book.class);
        return res.getBody();
    }

    @Override
    public void save(Book book) {
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var entity = new HttpEntity(book, headers);
        restTemplate.exchange(
                URL,
                POST,
                entity,
                Book.class
        );
    }

    @Override
    public void delete(Integer id) {
//        restTemplate.delete(URL + "/" + id);
        restTemplate.exchange(
                URL + "/" + id,
                DELETE,
                null,
                Void.class
        );
    }

    @Override
    public void update(Integer id, Book book) {
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var entity = new HttpEntity(book, headers);
        restTemplate.exchange(
                URL + "/" + id,
                PUT,
                entity,
                Book.class
        );
    }

}
