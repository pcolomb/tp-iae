package fr.elfoa.hello.jpa;

import fr.elfoa.hello.rest.todo.Todo;
import fr.elfoa.hello.rest.todo.TodoListWS;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import java.net.URI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CrmJPAWS extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ClientWS.class);
    }

    @Test
    public void get() throws Exception {
        String string = target("Client").request().get(String.class);

        assertEquals("[{\"id\":1,\"mail\":\"toto@toto.fr\",\"nom\":\"toto\",\"telephone\":\"0614\",\"adresse\":null}]",string);
    }

    @Test
    public void get2() throws Exception {
        String string = target("Client/1").request().get(String.class);

        assertEquals("{\"id\":1,\"mail\":\"toto@toto.fr\",\"nom\":\"toto\",\"telephone\":\"0614\",\"adresse\":null}",string);
    }

    @Test
    public void post() throws Exception {

        MultivaluedMap<String,String> mm = new MultivaluedStringMap();
        mm.add("mail","tata.fr");
        mm.add("nom","tata");
        mm.add("tel","0612");
        Response response = target("Client").request().post(Entity.form(mm),Response.class);

        assertEquals(201, response.getStatus());

        String string = target("Client/2").request().get(String.class);

        assertEquals("{\"id\":2,\"mail\":\"tata.fr\",\"nom\":\"tata\",\"telephone\":\"0612\",\"adresse\":null}",string);
    }
}