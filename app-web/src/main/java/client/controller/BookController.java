package client.controller;

import client.config.ThymeleafTemplateEngine;
import client.dto.Author;
import client.dto.Book;
import client.srv.AuthorService;
import client.srv.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

import static client.config.Util.render;
import static spark.Spark.*;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    public void init() {
        path("/view", () -> {
            get("/books", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                model.put("books", bookService.getAll());

                return render(model, "books");
            });

            get("/book/nuevo", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                var authors = authorService.getAll();
                var book = new Book();
                book.setId(-1);
                model.put("book", book);
                model.put("authors", authors);

                return render(model, "agregar_libro");
            });

            get("/book/editar/:id", (req, res) -> {

                var book = bookService.findById(req.params(":id"));
                var authors = authorService.getAll();


                var model = new HashMap<String, Object>();
                model.put("book", book);
                model.put("authors", authors);

                return render(model, "agregar_libro");
            });

            post("/book", (req, res) -> {
                String body = req.body();
                var book = new Book();
                var author = new Author();
                var id = "0";
                for (String str : body.split("&")) {
                    var index = str.indexOf("=");
                    var key = str.substring(0, index);
                    var value = str.substring(index + 1).replace("+", " ");
                    if (key.equals("title")) {
                        book.setTitle(value);
                    } else if (key.equals("isbn")) {
                        book.setIsbn(value);
                    } else if (key.equals("price")) {
                        book.setPrice(Double.parseDouble(value));
                    } else if (key.equals("id")) {
                        id = value;
                    } else if (key.equals("author_id")) {
                        author.setId(Integer.parseInt(value));
                    }
                }
                book.setAuthor(author);
                if (id.equals("-1")) {
                    bookService.save(book);
                } else {
                    bookService.update(Integer.parseInt(id), book);
                }
                res.redirect("/view/books");
                return null;

            }, new ThymeleafTemplateEngine());

            get("/book/eliminar/:id", (req, res) -> {
                bookService.delete(Integer.parseInt(req.params(":id")));
                res.redirect("/view/books");
                return null;

            }, new ThymeleafTemplateEngine());
        });
    }
}
