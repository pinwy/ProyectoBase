package com.base.luiss.proyectobase.modelo;

/**
 * Created by luiss on 26/07/2016.
 */
public class currentWeather {
    private String Location;
    private String Time;
    private String Wind;
    private String Visibility;
    private String SkyConditions;
    private String Temperature;
    private String DewPoint;
    private String RelativeHumidity;
    private String Pressure;
    private String Status;

    public currentWeather(String location, String time, String wind, String visibility, String skyConditions, String temperature, String dewPoint, String relativeHumidity, String pressure, String status) {
        Location = location;
        Time = time;
        Wind = wind;
        Visibility = visibility;
        SkyConditions = skyConditions;
        Temperature = temperature;
        DewPoint = dewPoint;
        RelativeHumidity = relativeHumidity;
        Pressure = pressure;
        Status = status;
    }

    public currentWeather() {

    }



    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public String getVisibility() {
        return Visibility;
    }

    public void setVisibility(String visibility) {
        Visibility = visibility;
    }

    public String getSkyConditions() {
        return SkyConditions;
    }

    public void setSkyConditions(String skyConditions) {
        SkyConditions = skyConditions;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getDewPoint() {
        return DewPoint;
    }

    public void setDewPoint(String dewPoint) {
        DewPoint = dewPoint;
    }

    public String getRelativeHumidity() {
        return RelativeHumidity;
    }

    public void setRelativeHumidity(String relativeHumidity) {
        RelativeHumidity = relativeHumidity;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
