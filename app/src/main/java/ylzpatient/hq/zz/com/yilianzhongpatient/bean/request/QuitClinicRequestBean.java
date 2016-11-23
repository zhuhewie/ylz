package ylzpatient.hq.zz.com.yilianzhongpatient.bean.request;

/**
 * Created by Administrator on 2016-11-10.
 * 放弃在线问诊的请求
 */

public class QuitClinicRequestBean {

    /**
     * ClinicId : sample string 1
     * Reason : sample string 2
     */

    private String ClinicId;//问诊ID
    private String Reason;  //结束原因

    public String getClinicId() {
        return ClinicId;
    }

    public void setClinicId(String ClinicId) {
        this.ClinicId = ClinicId;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    @Override
    public String toString() {
        return "QuitClinicRequestBean{" +
                "ClinicId='" + ClinicId + '\'' +
                ", Reason='" + Reason + '\'' +
                '}';
    }
}
