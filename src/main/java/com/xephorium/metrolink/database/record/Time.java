/*
  Christopher Cruzen                        LaunchCode CS50
  "Time.java"                                    05.17.2015

  Problem Set #6

    This class represents a single time in HH:MM:SS format.
  Conventional classes like java.time (1.8) or Joda-Time
  are elegant and would usually do the trick, but exclude
  hour values over 23, which causes errors in Metrolink
  database extraction. This Time class accepts time values
  of up to 47:59:59.

*/
package com.xephorium.metrolink.database.record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time
{
    /*--- Fields ---*/

    private static final int MAX_HOURS   = 47;
    private static final int MAX_MINUTES = 59;
    private static final int MAX_SECONDS = 59;

    int hour;
    int minute;
    int second;


    /*--- Constructor ---*/

    public Time(int h, int m, int s)
    {
        internalValid(h, m, s);
        hour   = h;
        minute = m;
        second = s;
    }


    /*--- Methods ---*/

    public String toString()
    {
        StringBuilder string = new StringBuilder();
        if(hour < 10)
            string.append("0");
        string.append(hour);
        string.append(":");
        if(minute < 10)
            string.append("0");
        string.append(minute);
        string.append(":");
        if(second < 10)
            string.append("0");
        string.append(second);
        return string.toString();
    }

    public boolean equals(Time t)
    {
        return( reduceHour(hour) == reduceHour(t.getHour()) &&
                t.getMinute()    == minute &&
                t.getSecond()    == second);
    }

    public Time until(Time t)
    {
        // Copy/Reduce Time Values
        Time thisTime  = new Time(hour, minute, second);
        Time paramTime = new Time(t.getHour(), t.getMinute(), t.getSecond());
        thisTime.setHour(reduceHour(thisTime.getHour()));
        paramTime.setHour(reduceHour(paramTime.getHour()));

        // Parameter Same As This
        if(thisTime.equals(paramTime))
            return new Time(0, 0, 0);

        // Perform Circular Subtraction
        int hoursUntil   = (paramTime.getHour()   - thisTime.getHour());
        int minutesUntil = (paramTime.getMinute() - thisTime.getMinute());
        int secondsUntil = (paramTime.getSecond() - thisTime.getSecond());
        if(secondsUntil < 0)
        {
            secondsUntil = 60 + secondsUntil;
            minutesUntil--;
        }
        if(minutesUntil < 0)
        {
            minutesUntil = 60 + minutesUntil;
            hoursUntil--;
        }
        if(hoursUntil < 0)
            hoursUntil = 24 + hoursUntil;

        // Return Time
        return new Time(hoursUntil, minutesUntil, secondsUntil);

    }

    public void setTime(int h, int m, int s)
    {
        internalValid(h, m, s);
        hour = h;
        minute = m;
        second = s;
    }

    public void setHour(int h)
    {
        internalValid(h, 0, 0);
        hour = h;
    }

    public void setMinute(int m)
    {
        internalValid(0, m, 0);
        minute = m;
    }

    public void setSecond(int s)
    {
        internalValid(0, 0, s);
        second = s;
    }

    public int getHour()
    {
        return hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public int getSecond()
    {
        return second;
    }

    // Legacy Code - Only To Be Used On Reduced Times
    public boolean laterThan(Time t)
    {
        if(this.getHour() > t.getHour())
            return true;
        else if(this.getHour() == t.getHour() && this.getMinute() > t.getMinute())
            return true;
        else if(this.getMinute() == t.getMinute() && this.getSecond() > t.getSecond())
            return true;
        else
            return false;
    }

    // Legacy Code - Only To Be Used On Reduced Times
    public boolean earlierThan(Time t)
    {
        if(this.getHour() < t.getHour())
            return true;
        else if(this.getHour() == t.getHour() && this.getMinute() < t.getMinute())
            return true;
        else if(this.getMinute() == t.getMinute() && this.getSecond() < t.getSecond())
            return true;
        else
            return false;
    }


    /*--- Private Methods ---*/

    private void internalValid(int h, int m, int s)
    {
        if(h < 0 || h > MAX_HOURS)
            throw new RuntimeException("Value " + h + " for hour must be in the range [0," + MAX_HOURS + "].");
        if(m < 0 || m > MAX_MINUTES)
            throw new RuntimeException("Value " + m + " for minute must be in the range [0," + MAX_MINUTES + "].");
        if(s < 0 || s > MAX_SECONDS)
            throw new RuntimeException("Value " + s + " for second must be in the range [0," + MAX_SECONDS + "].");
    }


    /*--- Public Static Methods ---*/

    public static boolean valid(int h, int m, int s)
    {
        if(h < 0 || h > MAX_HOURS)
            return false;
        else if(m < 0 || m > MAX_MINUTES)
            return false;
        else if(s < 0 || s > MAX_SECONDS)
            return false;
        return true;
    }

    public static Time convertString(String str)
    {
        int h   = Integer.parseInt(str.substring(0,2));
        int m = Integer.parseInt(str.substring(3,5));
        int sec = Integer.parseInt(str.substring(6,8));
        return new Time(h, m, sec);
    }

    public static int reduceHour(int h)
    {
        if(h == 0)
            return h;
        else
            return h % 24;
    }

    public static Time getCurrent()
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = dateFormat.format(Calendar.getInstance().getTime());
        return Time.convertString(currentTime);
    }
}
