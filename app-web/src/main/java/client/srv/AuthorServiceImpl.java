package client.srv;

import client.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final String URL = "http://localhost:7071/authors";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Author> getAll() {
        var res = restTemplate.getForEntity(URL, Author[].class);
        return List.of(res.getBody());
    }

    @Override
    public Author findById(String id) {
        var res = restTemplate.getForEntity(URL + "/" + id, Author.class);
        return res.getBody();
    }

    @Override
    public void save(Author author) {
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var entity = new HttpEntity(author, headers);
        restTemplate.exchange(
                URL,
                POST,
                entity,
                Author.class
        );
    }

    @Override
    public void delete(Integer id) {
        restTemplate.exchange(
                URL + "/" + id,
                DELETE,
                null,
                Void.class
        );
    }

    @Override
    public void update(Integer id, Author author) {
        var headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        var entity = new HttpEntity(author, headers);
        restTemplate.exchange(
                URL + "/" + id,
                PUT,
                entity,
                Author.class
        );
    }
}
