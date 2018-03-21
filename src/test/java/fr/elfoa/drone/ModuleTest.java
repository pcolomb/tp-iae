package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import fr.elfoa.AbstractBootstraper;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.inject.Inject;

public class ModuleTest extends AbstractBootstraper {
    @Inject
    private Module module;

    @BeforeClass
    public static void start() {
        init();
    }

    @AfterClass
    public static void stop() {
        shutdown();
    }

    /*
    // Sans @Inject, sans AbstractBootstraper...
    @Before
    public void setUp() {
        module = new Module();
    }
    */

    @Test
    public void full() {
        module = getInstance(Module.class);
        assertEquals(module.max_power, module.getPower().intValue());
    }

    @Test
    public void use() {
        module = getInstance(Module.class);
        try {
            module.use(42);
        } catch (Exception e) {}
        assertEquals(module.max_power-42, module.getPower().intValue());
    }
}
