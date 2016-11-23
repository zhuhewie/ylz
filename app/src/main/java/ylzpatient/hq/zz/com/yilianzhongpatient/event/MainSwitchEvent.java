package ylzpatient.hq.zz.com.yilianzhongpatient.event;

import ylzpatient.hq.zz.com.yilianzhongpatient.base.SuperFragment;

/**
 * Created by Administrator on 2016-9-30.
 */
public class MainSwitchEvent {
    public SuperFragment toFragment;

    public MainSwitchEvent(SuperFragment toFragment) {
        this.toFragment = toFragment;
    }
}
