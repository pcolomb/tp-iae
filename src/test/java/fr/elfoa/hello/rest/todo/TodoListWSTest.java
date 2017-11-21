package fr.elfoa.hello.rest.todo;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.net.URI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
public class TodoListWSTest extends JerseyTest{

    @Override
    protected Application configure() {
        return new ResourceConfig(TodoListWS.class);
    }


    @Test
    public void get() throws Exception {
        String string = target("todo").request().get(String.class);

        assertEquals("[{\"name\":\"foo\",\"dueDate\":null,\"done\":false},{\"name\":\"bar\",\"dueDate\":null,\"done\":false},{\"name\":\"wiz\",\"dueDate\":null,\"done\":false}]", string);

        Todo[] todos = target("todo").request().get(Todo[].class);

        Todo[] expected = new Todo[]{new Todo("foo"),new Todo("bar"),new Todo("wiz")};

        assertArrayEquals(expected, todos);
    }

    @Test
    public void get2() throws Exception {
        String string = target("todo/1").request().get(String.class);

        assertEquals("{\"name\":\"foo\",\"dueDate\":null,\"done\":false}", string);

        Todo todo = target("todo/1").request().get(Todo.class);

        Todo expected = new Todo("foo");

        assertEquals(expected, todo);
    }

    @Test
    public void post() throws Exception {

        Response response = target("todo").request().post(Entity.form(new Form("text", "MyTodo")));

        assertEquals(201, response.getStatus());

        URI location = response.getLocation();

        Assert.assertEquals("/todo/4", location.getPath());

        Todo todo = target(location.getPath()).request().get(Todo.class);

        Todo expected = new Todo("MyTodo");

        assertEquals(expected, todo);
    }

    @Test
    public void delete() throws Exception {

        Response response = target("todo").request().post(Entity.form(new Form("text", "Temp Todo")));

        response = target(response.getLocation().getPath()).request().delete();

        assertEquals(204, response.getStatus());

        Todo[] todos = target("todo").request().get(Todo[].class);

        Todo[] expected = new Todo[]{new Todo("foo"),new Todo("bar"),new Todo("wiz")};

        assertArrayEquals(expected, todos);

    }

    @Test
    public void put() throws Exception {

        Response response = target("todo/1").request().put(Entity.form(new Form("done","true")));

        assertEquals(204, response.getStatus());

        Todo todo = target("todo/1").request().get(Todo.class);

        Todo expected = new Todo("foo");
        expected.setDone(true);

        assertEquals(expected, todo);

        target("todo/1").request().put(Entity.form(new Form("done","false")));

        todo = target("todo/1").request().get(Todo.class);

        assertEquals(expected, todo);

    }

}
