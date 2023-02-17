package client.controller;

import client.config.ThymeleafTemplateEngine;
import client.dto.Author;
import client.srv.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

import static client.config.Util.render;
import static spark.Spark.*;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
    public void init() {

        path("/view", () -> {
            get("/authors", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                model.put("authors", authorService.getAll()); 

                return render(model, "authors");
            });

            get("/author/nuevo", (req, res) -> {

                Map<String, Object> model = new HashMap<>();
                var author = new Author();
                author.setId(-1);
                model.put("author", author);

                return render(model, "agregar_autor");
            });

            get("/author/editar/:id", (req, res) -> {

                var author = authorService.findById(req.params(":id"));

                Map<String, Object> model = new HashMap<>();
                model.put("author", author);

                return render(model, "agregar_autor");
            });

            post("/author", (req, res) -> {
                String body = req.body();
                var author = new Author();
                var id = "0";
                for (String str : body.split("&")) {
                    var index = str.indexOf("=");
                    var key = str.substring(0, index);
                    var value = str.substring(index + 1).replace("+", " ");

                    if (key.equals("firstName")) {
                        author.setFirstName(value);
                    } else if (key.equals("lastName")) {
                        author.setLastName(value);
                    } else if (key.equals("id")) {
                        id = value;
                    }

                }

                if (id.equals("-1")) {
                    authorService.save(author);
                } else {
                    authorService.update(Integer.parseInt(id), author);
                }
                res.redirect("/view/authors");
                return null;

            }, new ThymeleafTemplateEngine());

            get("/author/eliminar/:id", (req, res) -> {
                authorService.delete(Integer.parseInt(req.params(":id")));
                res.redirect("/view/authors");
                return null;
            });
        });

    }
}
