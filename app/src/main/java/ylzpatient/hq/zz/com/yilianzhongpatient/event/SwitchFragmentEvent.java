package ylzpatient.hq.zz.com.yilianzhongpatient.event;

/**
 * Created by Administrator on 2016-10-13.
 */
public class SwitchFragmentEvent {
    //type = 1: 进入诊室界面
    public int type;

    public SwitchFragmentEvent(int type) {
        this.type = type;
    }
}
