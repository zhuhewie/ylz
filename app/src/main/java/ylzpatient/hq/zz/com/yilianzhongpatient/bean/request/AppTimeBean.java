package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-9-19.
 * 用于获取预约医生排班表的实体
 */
public class AppTimeBean {

    /**
     * BeginTime : 2016-09-20
     * Dept : 7
     * Doctor : 1084
     * EndTime : 2016-10-20
     */

    public String BeginTime; //开始时间
    public String Dept; //科室编码
    public String Doctor; //医生id
    public String EndTime; //预约最晚时间

    public AppTimeBean(String beginTime, String dept, String doctor, String endTime) {
        BeginTime = beginTime;
        Dept = dept;
        Doctor = doctor;
        EndTime = endTime;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getDept() {
        return Dept;
    }

    public void setDept(String dept) {
        Dept = dept;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    @Override
    public String toString() {
        return "AppTimeBean{" +
                "BeginTime='" + BeginTime + '\'' +
                ", Dept='" + Dept + '\'' +
                ", Doctor='" + Doctor + '\'' +
                ", EndTime='" + EndTime + '\'' +
                '}';
    }
}
