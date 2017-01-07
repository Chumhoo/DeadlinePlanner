package awesome.deadlineplanner;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chumhoo on 17/1/7.
 */

public class Time {

    Date deadDate;
    private int[] monthDay = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Time(Date deadDate) {
        this.deadDate = deadDate;
    }

    public int getRemainSecond() {
        Date current = new Date();
        return (int) (deadDate.getTime() - current.getTime()) / 1000;
    }

    public int getRemainMinute() {
        Date current = new Date();
        return (int) (deadDate.getTime() - current.getTime()) / 1000 / 60;
    }

    public int getRemainHour() {
        Date current = new Date();
        if (deadDate.getHours() > current.getHours()) {
            return deadDate.getHours() - current.getHours();
        } else {
            return deadDate.getHours() + 24 - current.getHours();
        }
    }

    public int getRemainDay() {
        Date current = new Date();
        int currentYear = current.getYear() - 100 + 2000;
        int remainDays = 0;
        for (int year = currentYear; year <= deadDate.getYear(); year++) {
            int month;
            if (year == currentYear) month = current.getMonth();
            else month = 0;
            for (; month < 12; month++) {
                if (year == deadDate.getYear())
                    if (month == (deadDate.getMonth() - 1))
                        break;
                remainDays += monthDay[month];
            }
        }
        remainDays += deadDate.getDate();
        remainDays -= current.getDay();
        return remainDays;
    }

}
