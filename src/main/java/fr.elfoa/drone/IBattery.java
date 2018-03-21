package fr.elfoa.drone;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public interface IBattery {

    @Inject
    List<Module> modules = new ArrayList<>();

    public void use(Integer power);
    public Integer getPower();
}
