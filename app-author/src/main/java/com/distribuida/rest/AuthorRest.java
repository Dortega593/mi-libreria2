package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.servicios.AuthorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/authors")
public class AuthorRest {

    @Inject
    AuthorService authorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> list() {
        return authorService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Author get(@PathParam("id") Long id) {
        return authorService.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Author author) {
        try {
            authorService.save(author);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("No se pudo guardar")
                    .build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") long id, Author author) throws Exception {
        try {
            authorService.update(id, author);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("No se pudo actualizar" + ex.getMessage())
                    .build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        try {
            authorService.delete(id);
        } catch (Exception ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("No se pudo eliminar")
                    .build();
        }
        return Response.ok().build();
    }

}
