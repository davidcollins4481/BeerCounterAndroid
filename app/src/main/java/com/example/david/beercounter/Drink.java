package com.example.david.beercounter;

import android.text.format.DateUtils;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Drink extends SugarRecord<Drink> {
    private long consumptiondate;
    private String type = "Base";

    public Drink() {
        // store in UNIX time
        this.consumptiondate = System.currentTimeMillis() / 1000L;
        this.type = this.getType();
    }

    public String getType() {
        return this.type;
    }

    public static List<Drink> getToday() {
        // today
        Calendar midnight = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        midnight.set(Calendar.SECOND, 0);

        long midnightUnix = midnight.getTimeInMillis() / 1000L;

        // today
        Calendar endOfDay = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        endOfDay.set(Calendar.HOUR_OF_DAY, 23);
        endOfDay.set(Calendar.MINUTE, 59);
        endOfDay.set(Calendar.SECOND, 59);

        long elevenFiftyNine = endOfDay.getTimeInMillis() / 1000L;


        List<Drink> result = Drink.findWithQuery(Drink.class,
                "SELECT * FROM DRINK WHERE consumptiondate >= ? AND consumptiondate <= ?",
                midnightUnix + "", elevenFiftyNine + "");


        return result;
    }
}
