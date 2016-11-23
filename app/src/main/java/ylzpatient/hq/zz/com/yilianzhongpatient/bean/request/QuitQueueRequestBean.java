package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-9.
 * 退出排队请求
 */

public class QuitQueueRequestBean {

    /**
     * QueueSn : sample string 1  排队编号
     * IsPush : true
     */

    private String QueueSn; //排队编号
    private boolean IsPush ; //是否推送给其他排队用户 手动点退出排队按钮的时候这个传false


    public String getQueueSn() {
        return QueueSn;
    }

    public void setQueueSn(String QueueSn) {
        this.QueueSn = QueueSn;
    }

    public boolean isIsPush() {
        return IsPush;
    }

    public void setIsPush(boolean IsPush) {
        this.IsPush = IsPush;
    }
}
