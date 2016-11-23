package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-4.
 * 获取检验报告主列表的请求实体
 */

public class MainInspeRequest {

    /**
     * PatientID : 194609
     * BeginDate : 2011-12-1
     * EndDate : 2016-12-30
     */

    private String PatientID;
    private String BeginDate;
    private String EndDate;

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getBeginDate() {
        return BeginDate;
    }

    public void setBeginDate(String BeginDate) {
        this.BeginDate = BeginDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    @Override
    public String toString() {
        return "MainInspeRequest{" +
                "PatientID='" + PatientID + '\'' +
                ", BeginDate='" + BeginDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                '}';
    }
}
