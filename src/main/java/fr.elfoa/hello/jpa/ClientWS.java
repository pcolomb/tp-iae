package fr.elfoa.hello.jpa;

import fr.elfoa.hello.rest.todo.Todo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("Client")
public class ClientWS {

    private static final Logger LOG = Logger.getLogger(ClientWS.class.getCanonicalName());
    private static final List<Client> clients = new ArrayList<Client>();

    static {
        Adresse a = new Adresse("A",1,"b","a","AZ");
        List<Adresse> al = new ArrayList<Adresse>();
        al.add(a);
        clients.add(new Client(1,"toto@toto.fr","toto","0614",null));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(clients.stream()
                .toArray(Client[]::new))
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") Integer id) {
        return Response.ok(clients.stream().filter(client -> client.getId() == id).findFirst().get())
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("mail") String mail,@FormParam("nom") String nom, @FormParam("tel") String telephone) {

        int id = clients.get(clients.size()-1).getId() + 1;
        clients.add(new Client(id,mail,nom,telephone,null));

        return Response.created(URI.create("client/" + clients.get(clients.size()-1)))
                .build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") Integer id) {
        clients.removeIf(c -> c.getId() == id);
        return Response.noContent()
                .build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response check(@PathParam("id") Integer id, @FormParam("nom") String nom) {
        clients.stream().filter(client -> client.getId() == id).findFirst().get().setNom(nom);
        return Response.noContent()
                .build();
    }
}
