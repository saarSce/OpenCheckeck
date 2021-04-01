package com.company.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HTTPHandler {

    private static final int TIMEOUT = 15*1000;

    /**
     * Open HttpURLConnection at @path - URL address ;
     * @param path URL address.
     * @return Opened HttpURLConnection at requested @path
     */
    public static HttpURLConnection getHTTP(String path){
        HttpURLConnection con = null;
        try {
            URL url = new URL(path);
            con =
                    (HttpURLConnection)url.openConnection();
            con.setConnectTimeout(TIMEOUT);
            int statusCode = con.getResponseCode();
            InputStream in = con.getInputStream();
            return con;
        } catch (MalformedURLException e) {
            System.out.println("HTTPHandler error - "+e.getMessage());
        } catch (IOException e) {
            System.out.println("HTTPHandler  error - "+e.getMessage());
        }
        return null;
    }

}
