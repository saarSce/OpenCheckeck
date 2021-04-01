package com.company.utils;

import com.company.Sampler;
import com.company.entities.City;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class IOUtils {

    /**
     * Read from a file at a given @path And returns A Samplers' ArrayList.
     *
     * @param path the path of the file to be read.
     * @return Samplers' ArrayList contains cities from file.
     */
    public static ArrayList<Sampler> readCitiesFromFile(String path) {
        ArrayList<Sampler> samplers = null;
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonCitiesArray = (JSONArray) parser.parse(new FileReader(path));

            samplers = new ArrayList<>(jsonCitiesArray.size());
            for (Object o : jsonCitiesArray) {
                Sampler s = new Sampler(new City((JSONObject) o));
                samplers.add(s);
            }
            return samplers;
        } catch (FileNotFoundException e) {
            System.out.println("Cities File - file not found");
        } catch (IOException e) {
            System.out.println("Cities File - IO exception");
        } catch (ParseException e) {
            System.out.println("Cities File - Parse exception");
        }
        return null;
    }

    /**
     * Read from Opened HttpURLConnection and returns a string.
     * @param con Opened HttpURLConnection.
     * @return String represent url request result;
     */
    public static String read(HttpURLConnection con) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream in = con.getInputStream();
            String lineSep = "\n";
            reader =
                    new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(lineSep);
            }
            return builder.toString();
        } catch (IOException e) {
            System.out.println("Read Http Error - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Read Http Error - " + e.getMessage());
        } finally {
            if (con != null)
                con.disconnect();
            close(reader);
        }

        return null;
    }

    /**
     * Close all the closable objects in one place
     *
     * @param c The Closable to close
     */
    private static void close(Closeable... c) {
        try {
            if (c != null)
                for (Closeable closeable : c) {
                    closeable.close();
                }
        } catch (NullPointerException e) {
            return;
        } catch (IOException e) {
            System.out.println("Close Error - " + e.getMessage());

        }
    }


}
