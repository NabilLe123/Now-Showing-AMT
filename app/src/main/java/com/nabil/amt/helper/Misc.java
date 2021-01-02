package com.nabil.amt.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Misc {

    public static String convertDateFormat(final String date) {
        try {
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date newDate = spf.parse(date);
            spf = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            return spf.format(newDate);
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }
}
