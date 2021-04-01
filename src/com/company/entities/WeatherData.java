package com.company.entities;

import org.json.simple.JSONObject;


public class WeatherData {

    private double temp;

    public WeatherData(JSONObject json) {
        //parse this json to the fields
        this.temp = Double.parseDouble((json.get("temp")).toString());
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "WeatherData [temp = " + temp + "]";
    }
}
