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
    public void Test_1_Get() throws Exception {
        String string = target("Client").request().get(String.class);

        assertEquals("[{\"id\":1,\"mail\":\"toto@toto.fr\",\"nom\":\"toto\",\"telephone\":\"0614\",\"adresse\":null}]",string);
    }

    @Test
    public void Test_2_Get2() throws Exception {
        String string = target("Client/1").request().get(String.class);

        assertEquals("{\"id\":1,\"mail\":\"toto@toto.fr\",\"nom\":\"toto\",\"telephone\":\"0614\",\"adresse\":null}",string);
    }

    @Test
    public void Test_3_Post() throws Exception {

        MultivaluedMap<String,String> mm = new MultivaluedStringMap();
        mm.add("mail","tata.fr");
        mm.add("nom","tata");
        mm.add("tel","0612");
        Response response = target("Client").request().post(Entity.form(mm),Response.class);

        assertEquals(201, response.getStatus());

        String string = target("Client/2").request().get(String.class);

        assertEquals("{\"id\":2,\"mail\":\"tata.fr\",\"nom\":\"tata\",\"telephone\":\"0612\",\"adresse\":null}",string);

        response = target("Client/2").request().delete();
    }

    @Test
    public void Test_5_Delete() throws Exception
    {
        MultivaluedMap<String,String> mm = new MultivaluedStringMap();
        mm.add("mail","tata.fr");
        mm.add("nom","tata");
        mm.add("tel","0612");
        Response resp = target("Client").request().post(Entity.form(mm),Response.class);

        assertEquals(201, resp.getStatus());

        Response response = target("Client/2").request().delete();

        assertEquals(204, response.getStatus());

        String string = target("Client").request().get(String.class);

        assertEquals("[{\"id\":1,\"mail\":\"toto@toto.fr\",\"nom\":\"toto\",\"telephone\":\"0614\",\"adresse\":null}]",string);
    }

    @Test
    public void Test_4_Put() throws Exception {

       MultivaluedMap<String,String> mm = new MultivaluedStringMap();
        mm.add("mail","tata.fr");
        mm.add("nom","tata");
        mm.add("tel","0612");
        Response rep = target("Client").request().post(Entity.form(mm),Response.class);

        assertEquals(201, rep.getStatus());

        Response response = target("Client/2").request().put(Entity.form(new Form("nom", "Jean")));

        assertEquals(204, response.getStatus());

        String string = target("Client/2").request().get(String.class);

        assertEquals("{\"id\":2,\"mail\":\"tata.fr\",\"nom\":\"Jean\",\"telephone\":\"0612\",\"adresse\":null}",string);
    }
}