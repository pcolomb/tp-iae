package fr.elfoa.hello.rest;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Pierre Colomb
 */
@Path("hello")
public class HelloWorld {


    @GET
    public String getHello() {
                return "Hello World!";
            }
    @POST
    public String getHelloPost() {
        return "Hello World!";
    }
}
