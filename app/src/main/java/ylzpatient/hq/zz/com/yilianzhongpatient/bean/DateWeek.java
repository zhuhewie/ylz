package ylzpatient.hq.zz.com.yilianzhongpatient.bean;

/**
 * Created by Administrator on 2016-9-26.
 */
public class DateWeek {
    public String date;//几号
    public String week;//星期几

    public DateWeek() {
    }

    public DateWeek(String date, String week) {
        this.date = date;
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "DateWeek{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                '}';
    }
}
