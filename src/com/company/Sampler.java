package com.company;

import com.company.entities.City;
import com.company.utils.DateUtils;
import com.company.utils.HTTPHandler;
import com.company.utils.IOUtils;
import com.company.entities.Sample;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Sampler implements Runnable {

    private static final String API_CODE = "&appid=e735b6b632e6c008be941b8dbdb346d4";
    private static final String HTTP_START = "http://api.openweathermap.org/data/2.5/weather?id=";
    private static final String HTTP_UNITS = "&units=metric";

    private City city;
    private ArrayList<Sample> samplesHistory;

    public Sampler(City city) {
        this.city = city;
        this.samplesHistory = new ArrayList<>(0);
    }

    @Override
    public void run() {
        getCitySample();
    }

    /**
     * Adding new sample to city samples history.
     * notify user if there was a threshold change in temp.
     * @param newSample the new sample returned from API.
     */
    private void addNewSample(Sample newSample) {
        samplesHistory.add(newSample);
        // print new sample
        System.out.println(samplesHistory.get(samplesHistory.size() - 1));
        // checks threshold change
        if (samplesHistory.size() > 1)
            if (compareSamples(samplesHistory.get(samplesHistory.size() - 1), samplesHistory.get(samplesHistory.size() - 2)))
                System.out.println("There was a threshold change in temp in " + this.city.getName());
    }

    /**
     * Getting city Sample from API.
     */
    private void getCitySample()
    {
        String address = HTTP_START + this.city.getId() + HTTP_UNITS + API_CODE;
        String todayDate = DateUtils.getDate();
        HttpURLConnection in = HTTPHandler.getHTTP(address);
        String data = IOUtils.read(in);
        JSONParser parser = new JSONParser();
        try {
            JSONObject result = (JSONObject) parser.parse(data);
            System.out.println();
            addNewSample(new Sample(result, todayDate));
        } catch (ParseException e) {
            System.out.println("City Details - Parse api json error");
        } catch (Exception e) {
            System.out.println("City Details - There is an Error with this city At server");
        }
    }

    /**
     * Comparing two samples temperature, check threshold change.
     * @param previous one sample before @current to compare with.
     * @param current sample to check.
     * @return true if was threshold change, false if not.
     */
    private boolean compareSamples(Sample previous, Sample current) {
        double currentTemp = current.getMain().getTemp();
        double previousTemp = previous.getMain().getTemp();
        double diffPercent = Math.abs(1.0 - currentTemp / previousTemp) * 100;
        return diffPercent >= this.city.getThreshold();
    }

    public City getCity() {
        return city;
    }
}
