package fr.elfoa.hello.rest;


import javax.ws.rs.GET;
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

}
