package SensorParameters;
import HES.Systems.EnvironmentalSystem;

public class Temperature extends Sensors {
    private String wardName;
    private int wardID;
    private int target;
    public static final double TOLERANCE = 0.5;

    public Temperature(EnvironmentalSystem system, String wardName, int wardID, int target){
        super(system);
        this.wardName=wardName;
        this.wardID=wardID;
        this.target=target;
    }

    @Override
    public double readValue(){
        return system.getTempFeed().getTemperature(wardID);
    }

    @Override
    public double getWantedValue() {
        return target;
    }

    @Override
    public String getControlName(){
        return wardName;
    }

    @Override
    public boolean isDeviceOn() {
        return system.isHeatingOn(wardID);
    }

    @Override
    public boolean isOutOfRange() {
        // always Math.thing u want
        if(Math.abs(getValueSensed()-target)>TOLERANCE) {return true;}
        else {return false}
    }

    // here it's void because it acts: it switches the device. The caller doesn’t need an answer
    @Override
    public void control() {
        if(getValueSensed()>target+TOLERANCE && isDeviceOn()){
            system.turnHeatingOn(wardID, false);
        }
        else if(getValueSensed()<target-TOLERANCE && !isDeviceOn()){
            system.turnHeatingOn(wardID, true);
        }
    }

    @Override
    public String getDeviceName() {
        return "Heating";
    }
}
