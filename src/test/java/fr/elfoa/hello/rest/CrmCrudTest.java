package fr.elfoa.hello.rest;

import fr.elfoa.hello.jpa.Client;
import fr.elfoa.hello.rest.todo.TodoListWS;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import java.net.URI;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrmCrudTest extends JerseyTest
{
    @Override
    protected Application configure() {
        return new ResourceConfig(ClientListWS.class);
    }

    @Test
     public void test1_getClients() throws Exception {
        Client[] clients = target("client").request().get(Client[].class);

        Client[] expected = new Client[]{
                new Client(0,"Scooby", "Doo", "scoobydoo@mail.fr", "1111111111"),
                new Client(1,"Rogers", "Sammy", "sammy.rogers@mail.fr", "2222222222"),
                new Client(2,"Jones", "Fred", "fred.jones@mail.fr", "3333333333")
        };

        assertArrayEquals(expected, clients);
    }

    @Test
    public void test2_getFirstClient() throws Exception {
        String string = target("client/0").request().get(String.class);

        assertEquals("{\"id\":0,\"nom\":\"Scooby\",\"prenom\":\"Doo\",\"mail\":\"scoobydoo@mail.fr\",\"telephone\":\"1111111111\",\"adresses\":[]}", string);

        Client client = target("client/0").request().get(Client.class);

        Client expected = new Client(0,"Scooby", "Doo", "scoobydoo@mail.fr", "1111111111");

        assertEquals(expected, client);
    }

    @Test
    public void test3_postClient() throws Exception {

        Form form = new Form();
        form.param("nom", "Blake");
        form.param("prenom", "Daphné");
        form.param("mail", "daphne.blake@mail.fr");
        form.param("telephone", "4444444444");
        Response response = target("client").request().post(Entity.form(form));

        assertEquals(201, response.getStatus());

        URI location = response.getLocation();

        Assert.assertEquals("/client/3", location.getPath());

        Client client = target(location.getPath()).request().get(Client.class);

        Client expected = new Client(3, "Blake", "Daphné", "daphne.blake@mail.fr", "4444444444");

        assertEquals(client, expected);
    }

    @Test
    public void test4_deleteClient() throws Exception
    {
        Client[] clientsBeforeDelete = target("client").request().get(Client[].class);
        assertEquals(4, clientsBeforeDelete.length);

        Response response = target("client/2").request().delete();

        assertEquals(204, response.getStatus());

        Client[] clientsAfterDelete = target("client").request().get(Client[].class);
        assertEquals(3, clientsAfterDelete.length);
    }

    @Test
    public void test5_updateClient() throws Exception
    {
        Client expectedClientBeforeUpdate = new Client(1,"Rogers", "Sammy", "sammy.rogers@mail.fr", "2222222222");
        Client clientBeforeUpdate = target("client/1").request().get(Client.class);

        assertEquals(clientBeforeUpdate, expectedClientBeforeUpdate);

        Form form = new Form();
        form.param("nom", "nouveau_nom");
        Response response = target("client/1").request().put(Entity.form(form));

        assertEquals(204, response.getStatus());

        Client expectedClientAfterUpdate = new Client(1,"nouveau_nom", "Sammy", "sammy.rogers@mail.fr", "2222222222");
        Client clientAfterUpdate = target("client/1").request().get(Client.class);

        assertEquals(clientAfterUpdate, expectedClientAfterUpdate);
    }
}
