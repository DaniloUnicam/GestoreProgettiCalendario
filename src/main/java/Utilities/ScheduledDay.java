package Utilities;


public class ScheduledDay {
    private Calendar day;
    private Calendar month;
    private Calendar year;



    public ScheduledDay (Calendar day, Calendar month, Calendar year) {
        if (day != null || month != null || year != null){
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public Calendar getMonth() {
        return month;
    }

    public void setMonth(Calendar month) {
        this.month = month;
    }

    public Calendar getYear() {
        return year;
    }

    public void setYear(Calendar year) {
        this.year = year;
    }
}
