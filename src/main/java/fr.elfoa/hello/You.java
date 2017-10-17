package fr.elfoa.hello;

import javax.inject.Inject;

/**
 * @author Pierre Colomb
 */
public class You {

    @Inject
    private Polite bean;

    String run() {
        return bean.sayHello("You");
    }
}
