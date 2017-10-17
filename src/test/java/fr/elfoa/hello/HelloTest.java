package fr.elfoa.hello;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Pierre Colomb
 */
public class HelloTest extends AbstractBootstraper {


    @BeforeClass
    public static void start(){
        init();
    }

    @Test
    public void world(){

        World world = getInstance(World.class);

        Assert.assertEquals("Hello World",world.run());
    }

    @Test
    public void you(){

        You you = getInstance(You.class);

        Assert.assertEquals("Hello You",you.run());
    }

    @AfterClass
    public static void stop(){
        shutdown();
    }
}
