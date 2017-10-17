package fr.elfoa.hello;

import javax.inject.Inject;



public class World {

    @Inject
    private Polite bean;

    String run() {
        return bean.sayHello("World");
    }

}