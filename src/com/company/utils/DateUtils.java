package com.company.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * @return today's date string.
     */
    public static String getDate(){
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }
}
