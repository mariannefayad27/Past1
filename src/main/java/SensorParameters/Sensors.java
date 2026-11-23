package SensorParameters;
import HES.Systems.EnvironmentalSystem;

public abstract class Sensors {
    private double valueSensed;
    protected final EnvironmentalSystem system; //because protected can be used in subclasses

    public Sensors(EnvironmentalSystem system){
        this.system = system;
    }

    // Abstract methods that will all use but differently
    public abstract double readValue(); // Give new value (every sensor has a different way of reading new value)
    public abstract double getWantedValue();
    public abstract boolean isDeviceOn();
    public abstract boolean isOutOfRange();
    public abstract void control(); // turn it off or on depending
    public abstract String getControlName(); //what is being measured
    public abstract String getDeviceName(); // which machine does it switch on/off

    // Update value everytime, to put before getter because not yet defined once
    public double update(){
        valueSensed=readValue();
        control();
    }
    // Getters
    public double getValueSensed() {
        return valueSensed;
    }

    public String describe(){
        return getControlName() + " (wanted value: " + String.format("%.1f", getWantedValue()) + "): " + String.format("%.1f", getValueSensed()) + ", " + getDeviceName() + " is " + (isDeviceOn() ? "ON" : "OFF");
    }
}
