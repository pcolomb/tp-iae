package fr.elfoa.hello.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;

import static org.junit.Assert.assertEquals;

/**
 * @author Pierre Colomb
 */
public class HelloWorldTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(HelloWorld.class);
    }

    @Test
    public void test() {
        String hello = target("hello").request()
                                      .get(String.class);

        assertEquals("Hello World!", hello);
    }
}

