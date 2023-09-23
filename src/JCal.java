import java.text.SimpleDateFormat;
import java.util.*;

public class JCal {
    /*     1 January 2023  ≡  11 Dey 1401
       this class convert find JCal date using gregory date
       first it determine current gregory calendar date and
       find difference between base date (1 January 2023) and current date then,
       it will count same day difference between JCal base Date (11 Dey 1401)
       and return JCal current date
     */
    private final Date cal = Calendar.getInstance().getTime();

    private int currentYear;
    private String currentMonth;
    private int currentDay;

    private int dayDiff;

    String[] monthNames = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    public JCal() {
        // Determine Current Date
        setCurrentYear();
        setCurrentMonth();
        setCurrentDay();
        calcDayDiff(currentYear, currentMonth, currentDay);
    }

    private void calcDayDiff(int year, String month, int day) {
        int ans = 0;

        // Day for Years
        int baseYear = 2023;
        int yDiff = year - baseYear;
        ans += yDiff * 365;
        for (int i = baseYear; i < year; i++)
            if (isFeb29days(i))
                ans += 1;

        // Day for Months
        int index = Arrays.asList(monthNames).indexOf(month);
        int baseMonthIndex = 0;
        int baseDay = 1;
        if (index == baseMonthIndex) {
            // passed days of current month
            ans = day - baseDay;
        } else if (index > baseMonthIndex) {
            // add Full months days
            for (int i = baseMonthIndex; i < index; i++)
                ans += getMonthDays(monthNames[i], year);

            // Day for Days
            // passed days of current month
            ans += day - baseDay;
        }
        setDayDiff(ans);
    }

    private int getMonthDays(String month, int year) {
        switch (month) {
            case "Apr", "Jun", "Sep", "Nov" -> {
                return 30;
            }
            case "Feb" -> {
                if (isFeb29days(year))
                    return 29;
                else
                    return 28;
            }
            default -> {
                return 31;
            }
        }
    }

    private int getJMonthDays(int month, int year) {
        if (month > 0 && month < 13)
            if (month < 7)
                return 31;
            else if (month < 12)
                return 30;
            else
            if (isKabiseh(year))
                return 30;
            else
                return 29;
        else
            return -1;
    }

    private String JDate(int dayDiff) {
        int year = 1401;
        int month = 10;
        int day;

        if (dayDiff <= 19) {
            day = dayDiff + 11;
        } else {
            dayDiff -= 19;
            month++;
            // now it can be calculated from 1 bahman 1401
            while (dayDiff > getJMonthDays(month, year)) {
                dayDiff -= getJMonthDays(month, year);

                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
            day = dayDiff;
        }
        return String.format("%d-%d-%d", year, month, day);
    }

    private void setDayDiff(int dayDiff) {
        this.dayDiff = dayDiff;
    }

    private void setCurrentYear() {
        SimpleDateFormat yf = new SimpleDateFormat("yyyy", Locale.getDefault());
        currentYear = Integer.parseInt(yf.format(cal));
    }

    private void setCurrentMonth() {
        SimpleDateFormat mf = new SimpleDateFormat("MMM", Locale.getDefault());
        this.currentMonth = mf.format(cal);
    }

    private void setCurrentDay() {
        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.getDefault());
        this.currentDay = Integer.parseInt(df.format(cal));
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getDayDiff() {
        return dayDiff;
    }

    public String getCurrentJDate() {
        return JDate(getDayDiff());
    }

    public boolean isFeb29days(int year) {
        return year % 4 == 0;
    }

    public boolean isKabiseh(int year) {
        List<Integer> list = Arrays.asList(1, 5, 9, 13, 17, 22, 26, 30);
        return list.contains(year % 33);
    }

}
