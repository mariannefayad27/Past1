import HES.Systems.EnvironmentalSystem;
import SensorParameters.Humidity;
import SensorParameters.Pollution;
import SensorParameters.Sensors;
import SensorParameters.Temperature;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private ArrayList<Sensors> sensors = new ArrayList<>();
    private List<JLabel> labels= new List<>();
    private List<JPanel> panels= new List<>();
    private EnvironmentalSystem system;

    public Hospital(EnvironmentalSystem system){
        this.system=system;

        sensors.add(new Temperature(system, "North Ward", 0, 23));
        sensors.add(new Temperature(system, "Central Ward", 1, 22));
        sensors.add(new Temperature(system, "South Ward", 2, 20));
        sensors.add(new Humidity(system));
        sensors.add(new Pollution(system, 5));
    }

    public JPanel buildgrid(){
        JLabel textLabel = new JLabel();
        textLabel.setText("<html>  <br>  </html>");
    }
}
