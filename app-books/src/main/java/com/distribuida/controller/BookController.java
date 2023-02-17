package com.distribuida.controller;

import com.distribuida.dto.Book;
import com.distribuida.service.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.ExecutionException;

import static jakarta.ws.rs.core.Response.Status.ACCEPTED;

@ApplicationScoped
@Path("/books")
public class BookController {
    @Inject
    private BookService bookService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findOneById(@PathParam("id") long id) throws ExecutionException, InterruptedException {
        return this.bookService.findOne(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws ExecutionException, InterruptedException {
        return Response.status(ACCEPTED).entity(bookService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Book book) {
        this.bookService.update(id, book);
        return Response.accepted().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Book book) {
        this.bookService.save(book);
        return Response.accepted().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        this.bookService.delete(id);
        return Response.accepted().build();
    }
}
