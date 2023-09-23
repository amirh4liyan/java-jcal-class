import java.util.Calendar;
import java.util.Date;

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
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(String currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }


}
