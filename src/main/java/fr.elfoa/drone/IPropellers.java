package fr.elfoa.drone;

public interface IPropellers {
    void setBattery(Battery battery);
    void start();
    void stop();
    Integer getNumberOfPropelle();
    public Boolean getRunning();
}
