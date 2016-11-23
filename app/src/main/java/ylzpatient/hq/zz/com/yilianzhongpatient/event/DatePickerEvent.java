package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import java.util.Date;

/**
 * Created by Administrator on 2016-9-23.
 */
public class DatePickerEvent {
    public Date chooseDate;
    public String dateType;

    public DatePickerEvent(Date chooseDate, String dateType) {
        this.chooseDate = chooseDate;
        this.dateType = dateType;
    }
}
