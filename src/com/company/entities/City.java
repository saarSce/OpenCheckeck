package com.company.entities;

import org.json.simple.JSONObject;

public class City{

    private int id;
    private String name;
    private int frequency;
    private double threshold;

    public City(JSONObject json) {
        //parse this json to the fields
        this.id = ((Long) json.get("city_id")).intValue();
        this.name = ((String) json.get("city_name")).toString();
        this.frequency = ((Long) json.get("frequency")).intValue();
        this.threshold = Double.parseDouble((json.get("threshold")).toString());
    }

    public int getFrequency() {
        return frequency;
    }

    public double getThreshold() {
        return threshold;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                ", threshold=" + threshold +
                '}';
    }
}
