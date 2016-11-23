package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-3.
 * 获取绑定就诊卡列表的请求实体
 */

public class PatientIdCardsRequest {

    /**
     * UserId : sample string 1
     * HospitalId : sample string 2
     * OnlyMe : true
     */

    private String UserId;
    private String HospitalId;
    private boolean OnlyMe = true;

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

    public boolean isOnlyMe() {
        return OnlyMe;
    }

    public void setOnlyMe(boolean OnlyMe) {
        this.OnlyMe = OnlyMe;
    }

    @Override
    public String toString() {
        return "PatientIdCardsRequest{" +
                "UserId='" + UserId + '\'' +
                ", HospitalId='" + HospitalId + '\'' +
                ", OnlyMe=" + OnlyMe +
                '}';
    }
}
