package fr.elfoa.hello.rest;


import fr.elfoa.hello.jpa.Client;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Path("client")
public class ClientListWS
{
    private static List<Client> clients = new ArrayList<Client>();

    static {
        clients.add(new Client(0,"Scooby", "Doo", "scoobydoo@mail.fr", "1111111111"));
        clients.add(new Client(1,"Rogers", "Sammy", "sammy.rogers@mail.fr", "2222222222"));
        clients.add(new Client(2,"Jones", "Fred", "fred.jones@mail.fr", "3333333333"));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get()
    {
        return Response.ok(
                clients
                .stream()
                .toArray(Client[]::new)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{index}")
    public Response get(@PathParam("index") Integer index)
    {
        Client client = clients.get(index);
        return Response.ok(client).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("nom") String nom,
                           @FormParam("prenom") String prenom,
                           @FormParam("mail") String mail,
                           @FormParam("telephone") String telephone)
    {
        int lastId = clients.get(clients.size() - 1).getId();
        int newId = lastId+1;
        clients.add(new Client(newId, nom, prenom, mail, telephone));

        return Response.created(URI.create("client/"+newId)).build();
    }

    @DELETE
    @Path("{index}")
    public Response delete(@PathParam("index") Integer index)
    {
        clients.remove(index.intValue());
        return Response.noContent().build();
    }

    @PUT
    @Path("{index}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateName(@PathParam("index") Integer index, @FormParam("nom") String nom)
    {
        Client client = clients.get(index);
        client.setNom(nom);

        return Response.noContent().build();
    }
}
