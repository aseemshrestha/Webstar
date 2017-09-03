package com.webstar.util;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.Interval;

public class TimeLapse
{

    public static String toRelative(Date start, Date end) throws ParseException
    {
        if(start.equals(end) || end.before(start)){
            return "1 sec ago";
        }
        Interval interval = new Interval(start.getTime(), end.getTime());
        org.joda.time.Period period = interval.toPeriod();
        StringBuilder result = new StringBuilder();
        if (period.getYears() > 0) {
            result.append(period.getYears() + " years ");
        }
        if (period.getMonths() > 0) {
            result.append(period.getMonths() + " months ");
        }
        if (period.getDays() > 0) {
            result.append(period.getDays() + " days ");
        }
        if (period.getHours() > 0) {
            result.append(period.getHours() + " hrs ");
        }
        if (period.getMinutes() > 0 && period.getMinutes() < 60) {
            result.append(period.getMinutes() + " mins ");
        }
        if (period.getSeconds() > 0 && period.getSeconds() < 60) {
            result.append(period.getSeconds() + " sec ");
        }
        return result.toString() + "ago";

    }
}
