package SensorParameters;

import HES.Systems.EnvironmentalSystem;

public class Pollution extends Sensors {
    private int target=5;

    public Pollution(EnvironmentalSystem system, int target){
        super(system);
        this.target=target;
    }

    @Override
    public double readValue() {
        return system.getPollutionFeed().getPollution();
    }

    @Override
    public double getWantedValue() {
        return target;
    }

    @Override
    public boolean isDeviceOn() {
        return system.isAirPurifierOn();
    }

    @Override
    public boolean isOutOfRange() {
        return getValueSensed()>target;
    }

    @Override
    public void control() {
        if(getValueSensed()>target && !isDeviceOn()){
            system.turnAirPurifierOn(true);
        }
        else if(getValueSensed()<=target && isDeviceOn()){
            system.turnAirPurifierOn(false);
        }
    }

    @Override
    public String getControlName(){
        return "Pollution";
    }

    @Override
    public String getDeviceName() {
        return "Air Purifier";
    }


}
