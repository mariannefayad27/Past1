package SensorParameters;
import HES.Systems.EnvironmentalSystem;

public class Humidity extends Sensors {
    private int insideHumid;
    private int outsideHumid;

    public Humidity(EnvironmentalSystem system){
        super(system);
    }

    @Override
    public double readValue() {
        insideHumid= system.getHumidityFeed().getInsideHumidity();
        outsideHumid= system.getHumidityFeed().getOutsideHumidity();
        return  insideHumid;
    }

    @Override
    public double getWantedValue() {
        return 1.1*outsideHumid;
    }

    @Override
    public boolean isDeviceOn() {
        return system.isDehumidifierOn();
    }

    @Override
    public boolean isOutOfRange() {
        return insideHumid>getWantedValue();
    }

    @Override
    public void control() {
        if(insideHumid>getWantedValue() && !isDeviceOn()){
            system.turnDehumidifierOn(true);}
        else if(insideHumid<getWantedValue() && isDeviceOn()){
            system.turnDehumidifierOn(false);}
    }

    @Override
    public String getControlName(){
        return "Humidity";
    }

    @Override
    public String getDeviceName() {
        return "Dehumidifier";
    }

    @Override
    public String describe() {
        return getControlName() + ": inside " + insideHumid + "%, outside " + outsideHumid
                + "%, difference " + (insideHumid - outsideHumid) + "%, "
                + getDeviceName() + " " + (isDeviceOn() ? "ON" : "OFF");
    }
}
