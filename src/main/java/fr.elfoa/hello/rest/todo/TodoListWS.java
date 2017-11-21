package fr.elfoa.hello.rest.todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Path("todo")
public class TodoListWS {

    private static final Logger LOG = Logger.getLogger(TodoListWS.class.getCanonicalName());

    private static final TodoList todos = new TodoList();

    static {
        todos.add(1, new Todo("foo"))
             .add(2, new Todo("bar"))
             .add(3, new Todo("wiz"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(todos.getAll()
                                .stream()
                                .toArray(Todo[]::new))
                       .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") Integer id) {
        return Response.ok(todos.getbyId(id))
                       .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("text") String text) {

        todos.add(new Todo(text));

        return Response.created(URI.create("todo/" + todos.getLastId()))
                       .build();
    }



    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") Integer id) {
        todos.delete(id);
        return Response.noContent()
                       .build();
    }



    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response check(@PathParam("id") Integer id, @FormParam("done") String done) {
        if ("true".equals(done)) {
            todos.done(id);
        } else {
            todos.unDone(id);
        }

        return Response.noContent()
                       .build();
    }
}