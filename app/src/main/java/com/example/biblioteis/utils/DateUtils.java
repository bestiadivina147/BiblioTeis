package com.example.biblioteis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatDate(String inputDate) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            Date date = inputFormatter.parse(inputDate);
            return outputFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
