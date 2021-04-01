package com.company;

import com.company.utils.IOUtils;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherManager {

    private static String CITIES_PATH = ".\\res\\cities.json";
    private ArrayList<Sampler> samplers;

    public WeatherManager() {
        samplers = IOUtils.readCitiesFromFile(CITIES_PATH);
    }

    /**
     * The managing method of samplers.
     * schedule each sampler to execute in its frequency.
     */
    public void run()
    {
        // no samplers (cities)
        if(samplers== null || samplers.size()<1) {
            System.out.println("WeatherManager- There are no cities, check json file");
            return;
        }

        printCities();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(samplers.size());
        // Shutdown Hook - In case we want shutting scheduler down in end
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));

        for (Sampler sampler : samplers) {
            scheduler.scheduleAtFixedRate(sampler, 0, sampler.getCity().getFrequency(), TimeUnit.SECONDS);
        }
    }
    private void printCities()
    {
        System.out.println("Cities");
        for (Sampler sampler : this.samplers)
            System.out.println(sampler.getCity());
    }
}
