package ylzpatient.hq.zz.com.yilianzhongpatient.event;

/**
 * Created by Administrator on 2016-10-17.
 * 返回键处理的点击事件
 */

public class BackEvent {
    //type = 1:诊室见面返回事件
    //type = 2:退出登录
    //type = 11:退出诊室确认按钮点击
    public int type;
    public String[] backReason;

    public BackEvent(int type,String... backReason) {
        this.type = type;
        this.backReason = backReason;
    }
}
