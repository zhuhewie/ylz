package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-7.
 * 检查列表的请求实体
 */

public class CheckListRequestBean {

    /**
     * PatientID : 000014166300
     * AdmissTimes : 1
     * BeginDate : 2011-12-1
     * EndDate : 2016-12-30
     */

    private String PatientID;
    private int AdmissTimes;
    private String BeginDate;
    private String EndDate;

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public int getAdmissTimes() {
        return AdmissTimes;
    }

    public void setAdmissTimes(int AdmissTimes) {
        this.AdmissTimes = AdmissTimes;
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
        return "CheckListRequestBean{" +
                "PatientID='" + PatientID + '\'' +
                ", AdmissTimes=" + AdmissTimes +
                ", BeginDate='" + BeginDate + '\'' +
                ", EndDate='" + EndDate + '\'' +
                '}';
    }
}
