package com.company.entities;

import org.json.simple.JSONObject;


public class WindData {
    private String speed;

    public WindData(JSONObject json) {
        //parse this json to the fields
        this.speed = json.get("speed").toString();
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "WindData [speed = " + speed + "]";
    }
}
