package com.webstar.util;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.Interval;

public class TimeLapse
{

    public static String toRelative(Date start, Date end)
    {
        if(start.equals(end) || end.before(start)){
            return "1 sec ago";
        }
        Interval interval = new Interval(start.getTime(), end.getTime());
        org.joda.time.Period period = interval.toPeriod();
        StringBuilder result = new StringBuilder();
        if (period.getYears() > 0) {
            result.append(period.getYears()).append(" years ");
        }
        if (period.getMonths() > 0) {
            result.append(period.getMonths()).append(" months ");
        }
        if (period.getDays() > 0) {
            result.append(period.getDays()).append(" days ");
        }
        if (period.getHours() > 0) {
            result.append(period.getHours()).append(" hrs ");
        }
        if (period.getMinutes() > 0 && period.getMinutes() < 60) {
            result.append(period.getMinutes()).append(" mins ");
        }
        if (period.getSeconds() > 0 && period.getSeconds() < 60) {
            result.append(period.getSeconds()).append(" sec ");
        }
        return result.toString() + "ago";

    }
}
