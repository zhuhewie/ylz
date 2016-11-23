package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-9-14.
 * 用于请求预约科室和医生数据的实体
 */
public class TimeBean {
    public String BeginTime;
    public String EndTime;

    public TimeBean() {
    }

    public TimeBean(String beginTime, String endTime) {
        BeginTime = beginTime;
        EndTime = endTime;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String BeginTime) {
        this.BeginTime = BeginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    @Override
    public String toString() {
        return "TimeBean{" +
                "BeginTime='" + BeginTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                '}';
    }
}