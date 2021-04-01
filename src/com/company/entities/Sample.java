package com.company.entities;

import org.json.simple.JSONObject;


public class Sample
{
    private String date;
    private String name;
    private WindData wind;
    private WeatherData main;

    public Sample(JSONObject json, String date){
        //parse this json to the fields
        JSONObject weatherData = (JSONObject) json.get("main");
        JSONObject windData = (JSONObject) json.get("wind");

        this.main = new WeatherData(weatherData);
        this.wind = new WindData(windData);
        this.name = ((String) json.get("name")).toString();
        this.date= date;
    }

    public WindData getWind ()
    {
        return wind;
    }

    public void setWind (WindData wind)
    {
        this.wind = wind;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public WeatherData getMain ()
    {
        return main;
    }

    public void setMain (WeatherData main)
    {
        this.main = main;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", wind=" + wind +
                ", main=" + main +
                '}';
    }
}
