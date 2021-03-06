package view.view_model;

import javafx.beans.property.SimpleStringProperty;

public class Robot {

    private SimpleStringProperty sensors;
    private SimpleStringProperty actState;

    public Robot(String sensors, String actState){

        this.sensors = new SimpleStringProperty(sensors);
        this.actState = new SimpleStringProperty(actState);
    }

    public String getSensors() {
        return sensors.get();
    }

    public void setSensors (String sensors){
        this.sensors.set(sensors);
    }

    public String getActState(){
        return actState.get();
    }

    public void setActState (String  actState){
        this.actState.set(actState);
    }

}
