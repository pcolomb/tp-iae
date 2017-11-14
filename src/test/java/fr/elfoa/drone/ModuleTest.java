package fr.elfoa.drone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ModuleTest {
    private Module module;

    @Before
    public void setUp() {
        module = new Module();
    }

    @Test
    public void full() {
        assertEquals(module.max_power, module.getPower().intValue());
    }

    @Test
    public void use() {
        try {
            module.use(42);
        } catch (Exception e) {}
        assertEquals(module.max_power-42, module.getPower().intValue());
    }
}
