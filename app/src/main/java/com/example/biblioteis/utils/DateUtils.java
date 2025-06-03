package com.example.biblioteis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Gestiona las operaciones relacionadas con fechas de la aplicacion
 */
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

    public static Date String2Date (String fechaString) throws ParseException {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = inputFormatter.parse(fechaString);
        return date;

    }


    public static Date sumarDiasAFecha (Date date, int dias){
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(date);
        calendario.add(Calendar.DAY_OF_MONTH, dias);
        Date finalDate = calendario.getTime();
        return finalDate;
    }

}
