package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-10-28.
 * 取消绑定就诊卡的请求实体
 */

public class DelectCardBean {

    /**
     * UserId : sample string 1
     * HospitalId : sample string 2
     * PatientId : sample string 3
     */

    private String UserId;
    private String HospitalId;
    private String PatientId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String PatientId) {
        this.PatientId = PatientId;
    }

    @Override
    public String toString() {
        return "DelectCardBean{" +
                "UserId='" + UserId + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", PatientId='" + PatientId + '\'' +
                '}';
    }
}
