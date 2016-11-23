package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import ylzpatient.hq.zz.com.yilianzhongpatient.bean.request.TimeBean;

/**
 * Created by Administrator on 2016-9-28.
 * 选着预约时段的点击事件
 */
public class AppTimeEvent {
    public TimeBean selectTime;

    public AppTimeEvent(TimeBean selectTime) {
        this.selectTime = selectTime;
    }
}
